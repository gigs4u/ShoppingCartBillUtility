package com.checkoutcounter.domain.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.TaxCategory;


@Repository
public interface IProductDao {
	
	public ProductMaster fetchProductMaster(Integer prodId);
	public Integer addProductInventory( String productName, TaxCategory taxCategory, double price) ;
	public List<ProductMaster> getAllProductMasterInInventory();
}
