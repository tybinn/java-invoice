package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private HashMap<Product, Integer> products;

	public Invoice(){
		this.products = new HashMap<>();
	}
	public void addProduct(Product product) {
		products.put(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		// TODO: implement
	}

	public BigDecimal getNetPrice() {
		BigDecimal retVal = new BigDecimal(0);
		if(products.isEmpty()){
			return BigDecimal.ZERO;
		}else {
			for(Product product : products.keySet()){
				retVal = retVal.add(product.getPrice().multiply(new BigDecimal(products.get(product).toString())));
			}
			return retVal;

		}
	}

	public BigDecimal getTax() {
		if(products.isEmpty()){
			return BigDecimal.ZERO;
		}
		return null;
	}

	public BigDecimal getTotal() {
		BigDecimal retVal = new BigDecimal(0);
		if(products.isEmpty()){
			return BigDecimal.ZERO;
		}
		for(Product product : products.keySet()){
			retVal = retVal.add(product.getPrice().multiply(new BigDecimal(products.get(product).toString())));
		}
		return retVal;
	}
}
