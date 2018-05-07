/**
 * 
 */
package com.checkoutcounter.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.TaxCategory;
import com.checkoutcounter.domain.dao.IProductDao;
import com.checkoutcounter.domain.service.IProductMasterService;

/**
 * @author psanghan
 *
 */
@Service
public class ProductMasterServiceImpl implements IProductMasterService {
	
	@Autowired
	public IProductDao productDaoImpl; 
	
	public Integer createProductInInventory(String productName, TaxCategory taxCategory, double price){
		return productDaoImpl.addProductInventory( productName, taxCategory, price);
	}

	@Override
	public List<ProductMaster> getAllProductMaster() {
		return productDaoImpl.getAllProductMasterInInventory();
	}

}
