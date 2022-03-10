package com.web.jdbc;

public class item {
	
	private product product;
	private int quantity;

	public item() {
	}

	public item(product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public product getProduct() {
		return product;
	}

	public void setProduct(product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity() {
		this.quantity += 1;
	}
	public void decQuantity() {
		this.quantity -= 1;
	}
	
	public double totalPrice() {
		return getProduct().getPrice() * getQuantity();
	}

}
