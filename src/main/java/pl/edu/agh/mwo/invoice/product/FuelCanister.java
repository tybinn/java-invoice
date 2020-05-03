package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class FuelCanister extends Product {
    public FuelCanister(String name, BigDecimal price){
        super(name, price, new BigDecimal("0.23"));
    }

    public BigDecimal getPriceWithTax() {
        LocalDate today = LocalDate.now();
        if (today.getMonth().equals(Month.APRIL) || today.getDayOfMonth() == 26){
            return super.getPriceWithTax();
        }else{
            return super.getPriceWithTax().add(new BigDecimal("5.56") );
        }
    }
}
