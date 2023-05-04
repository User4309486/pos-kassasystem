package main.java.se.kth.iv1350.kassasystem.model;

import java.time.*;
import java.util.*;

/*
 * A sale being made for one customer
 */

public class Sale {
	private double totalVAT;
	private double totalCost;
	private LocalTime time = LocalTime.now();
	private List<Product> products = new ArrayList<>();
	private Receipt receipt;
	private List<Integer> numberOfProducts = new ArrayList<>();
	private DTOForSale orderInfo = new DTOForSale(products, totalVAT, totalCost, time);

	/**
	 * Creates a new instance of sale and saves the time.
	 */
	public Sale() {
	}

	/**
	 * Returns the DTOForSale object for this sale.
	 *
	 * @return the DTOForSale object for this sale
	 */
	public DTOForSale getOrderInfo() {
		return orderInfo;
	}

	/**
	 * Returns the list of products for this sale.
	 *
	 * @return the list of products for this sale
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Returns the receipt for this sale. If the receipt has not yet been generated,
	 * it will be created and returned.
	 *
	 * @return the receipt for this sale
	 */
	public Receipt getReceipt() {
		if (receipt == null) {
			this.receipt = new Receipt(orderInfo);
		}
		return receipt;
	}

	/**
	 * Returns a list containing the number of each product sold in this sale.
	 *
	 * @return a list containing the number of each product sold in this sale
	 */
	public List<Integer> getNumberOfProducts() {
		return numberOfProducts;
	}

	public void addProduct(int numberOfSetsOfProduct, Product product) {
		updateTotalVAT(numberOfSetsOfProduct, product.getDTOForProduct().getVAT());
		updateTotalCost(
				numberOfSetsOfProduct, product.getDTOForProduct().getVAT(), product.getDTOForProduct().getCost());
		productOfTheSameKindExists(numberOfSetsOfProduct, product);
	}

	/**
	 * Check if a product of the same kind already exists in the list of products,
	 * and updates the quantity of the existing product or adds a new product with
	 * the quantity.
	 * 
	 * @param number  the quantity of the product being sold
	 * @param product the product being sold
	 */

	private void productOfTheSameKindExists(int number, Product product) {
		boolean found = false;
		for (Product presentProduct : products) {
			if (presentProduct.getProductID() == product.getProductID()) {
				found = true;
				numberOfProducts.set(products.indexOf(presentProduct),
						(numberOfProducts.get(products.indexOf(presentProduct)) + number));
			}
		}
		if (found == false) {
			updateProducts(product);
			numberOfProducts.add(number);
		}
	}

	/**
	 * Updates the total cost of the sale by adding the cost of the sold products
	 * and their VAT to the current total cost, and updates the DTO.
	 * 
	 * @param number   the quantity of the sold products
	 * @param totalVAT the total VAT of the sold products
	 * @param amount   the price of one product
	 */
	private void updateTotalCost(int number, double totalVAT, double amount) {
		this.totalCost += (amount * number) + (totalVAT * (double) number);
		updateDTO();
	}

	/**
	 * Updates the total VAT of the sale by adding the VAT of the sold products to
	 * the current total VAT, and updates the DTO.
	 * 
	 * @param number the quantity of the sold products
	 * @param vat    the VAT of one product
	 */

	private void updateTotalVAT(int number, double vat) {
		this.totalVAT += (vat * number);
		updateDTO();
	}

	/**
	 * Adds a product to the list of products and updates the DTO.
	 * 
	 * @param product the product being sold
	 */

	private void updateProducts(Product product) {
		products.add(product);
		updateDTO();
	}

	/**
	 * Updates the DTO with the current information of the sale.
	 */

	private void updateDTO() {
		this.orderInfo = new DTOForSale(products, totalVAT, totalCost, time);
	}

}
