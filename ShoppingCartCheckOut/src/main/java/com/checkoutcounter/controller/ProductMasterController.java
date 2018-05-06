/**
 * 
 */
package com.checkoutcounter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.service.IProductMasterService;

/**
 * @author psanghan
 *
 */
@RestController
@RequestMapping("/productMaster")
public class ProductMasterController {


	private static final String template = "ProductId=%s Added in inventory";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductMasterController.class);

	@Autowired
	private IProductMasterService productMasterServiceImpl;

	/**
	 * API to Create a new Product for Inventory
	 * 
	 * @return
	 */
	// http://localhost:8080/productMaster/
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)	
	public String addProductInInventory(@RequestBody ProductMaster prodMaster) {
		LOGGER.info("Add New Product in Inventory");
		
		Integer prodId =productMasterServiceImpl.createProductInInventory(prodMaster.getProductName(), prodMaster.getTaxCategory(), prodMaster.getProdPrice());
		String returnString = String.format(template, prodId);
		return prodId.toString();
		//return String.format(template, prodId);
	}

}
