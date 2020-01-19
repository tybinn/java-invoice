package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) throws IllegalArgumentException{
		if (name == null || price == null || name.equals("") || price.compareTo(new BigDecimal("0")) == -1) {
			throw new IllegalArgumentException();
		} else{
			this.name = name;
		}
		this.price = price;
		this.taxPercent = tax;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return price.multiply(new BigDecimal( "1.08"));
	}
}
