/**
 * 
 */
package com.checkoutcounter.domain.service;

import java.util.List;

import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.TaxCategory;

/**
 * @author psanghan
 *
 */
public interface IProductMasterService {
	
	public Integer createProductInInventory(String productName, TaxCategory taxCategory, double price);

	public List<ProductMaster> getAllProductMaster();

}
