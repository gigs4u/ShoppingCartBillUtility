package com.checkoutcounter.domain.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.TaxCategory;
import com.checkoutcounter.domain.dao.IProductDao;


@Repository
public class ProductDaoImpl implements IProductDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);
	private final AtomicInteger atomicInt = new AtomicInteger();
	
	// TODO - Product Map is a temporary Map instead of PRODUCT master table
	private Map<Integer, ProductMaster> productMap = new ConcurrentHashMap<Integer, ProductMaster>();
	
	// TODO - This should be replaced by actual JDBC/JPA/Hibernate code.
	// Also have throws User defined Exception after catching SQL Exceptions
	
	@Override
	public ProductMaster fetchProductMaster(Integer productId) {		
		Assert.notNull(productId, "Product Id Cannot be Null-> "+productId);	
		return productMap.get(productId);
	}

	public Integer addProductInventory(String productName, TaxCategory taxCategory, double price) {
		//TODO: All null checks
		Integer prodId =  Integer.valueOf(atomicInt.incrementAndGet()); //some logic to create ProdId
		ProductMaster product = new ProductMaster(
				prodId,productName,price,taxCategory);
		// Put product into Map
		productMap.put(prodId, product);
		LOGGER.debug("Product Added-> "+product.getProdId());
		return prodId;
	}

	@Override
	public List<ProductMaster> getAllProductMasterInInventory() {
		return new ArrayList<ProductMaster>(productMap.values());
	}
	
}
