/**
 * 
 */
package com.checkoutcounter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checkoutcounter.domain.BillSumary;
import com.checkoutcounter.domain.service.impl.ShoppingCartService;

/**
 * Controller for Shopping Cart/Bill Generation
 * 
 * For ease of Testing purpose all the methods are made GET
 * 
 * /bill/ /bill/{billId} /bill/{billId}/addProductToCart
 * /bill/{billId}/checkOutCart
 * 
 * @author psanghan
 * 
 */

@RestController
@RequestMapping(path = "/bill")
public class ShoppingCartController {
	private static final String template = "BillId=%s";
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * API to Create a new Bill/Cart
	 * 
	 * @return
	 */
	@RequestMapping("/")
	// http://localhost:8080/bill/
	public String createNewCart() {
		LOGGER.info("shoppingCartService=" + shoppingCartService);
		Long cartId = shoppingCartService.createNewCartId();
		return String.format(template, cartId);
	}

	/**
	 * Fetch the Existing Bill Summary
	 * 
	 * @param billId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{billId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	// http://localhost:8080/bill/1525516382271
	public BillSumary fetchCartDetails(@PathVariable(value = "billId") Long billId) {
		LOGGER.info("billId =" + billId);
		return shoppingCartService.fetchCartDetails(billId);
	}

	/**
	 * Add Product to the existing Bill
	 * @param billId
	 * @param scannedProdId
	 * @param qty
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{billId}/addProductToCart", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	// http://localhost:8080/bill/1525516382271/addProductToCart?scannedProdId=111&quantity=1
	public BillSumary addProductToCart(@PathVariable("billId") long billId,
			@RequestParam("scannedProdId") Integer scannedProdId,
			@RequestParam(value = "quantity", defaultValue = "1") int qty) {
		LOGGER.info("Add Prod Id " + scannedProdId + " to billId =" + billId);

		shoppingCartService.addProductToCart(billId, scannedProdId, qty);
		return shoppingCartService.fetchCartDetails(billId);
	}

	/**
	 * Checkout the Bill
	 * 
	 * @param billId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/{billId}/checkOutCart", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	//http://localhost:8080/bill/1525521926606/checkOutCart
	public BillSumary checkOutCart(@PathVariable("billId") long billId) {
		LOGGER.info("Checkout of billId =" + billId);
		return shoppingCartService.checkoutCart(billId);
	}

}
