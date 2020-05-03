package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;
//import sun.security.timestamp.TSRequest;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (productExists(product.getName())){
            products.replace(getProductByName(product.getName()), quantity + products.get(getProductByName(product.getName())));
        }else{
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }

    public int getProductAmountByName(String name){
        Product tmpProd = getProductByName(name);
        if (tmpProd == null){
            return 0;
        }else{
            return products.get(tmpProd);
        }
    }

    private Product getProductByName(String name){
        for (Product product : products.keySet()) {
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    private boolean productExists(String name){
        if (getProductByName(name) == null){
            return false;
        }
        return true;
    }

    public String print(){
        String retVal = "Numer faktury: " + getNumber() + "\n";
        int i = 0;
        for (Product product : products.keySet()) {
            retVal = retVal + product.getName() + ", " + products.get(product) + ", " + product.getPrice().multiply(BigDecimal.valueOf(products.get(product))) + "\n";
            i++;
        }
        retVal = retVal + "Liczba pozycji: " + i;

        return retVal;
    }
}
