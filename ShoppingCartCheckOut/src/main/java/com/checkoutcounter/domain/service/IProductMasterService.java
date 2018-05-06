/**
 * 
 */
package com.checkoutcounter.domain.service;

import com.checkoutcounter.domain.TaxCategory;

/**
 * @author psanghan
 *
 */
public interface IProductMasterService {
	
	public Integer createProductInInventory(String productName, TaxCategory taxCategory, double price);

}
