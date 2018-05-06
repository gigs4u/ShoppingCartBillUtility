/**
 * 
 */
package com.checkoutcounter.domain.service.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkoutcounter.domain.BillSumary;
import com.checkoutcounter.domain.BillSumary.BillStatus;
import com.checkoutcounter.domain.IProduct;
import com.checkoutcounter.domain.ProductMaster;
import com.checkoutcounter.domain.TaxCategory;
import com.checkoutcounter.domain.dao.IBillSummaryDao;
import com.checkoutcounter.domain.dao.IProductDao;
import com.checkoutcounter.domain.service.IShoppingCartService;

/**
 * @author psanghan
 *
 */
@Service
public class ShoppingCartService implements IShoppingCartService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartService.class);
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	IBillSummaryDao billSummaryDaoImpl;
	@Autowired
	public IProductDao productDaoImpl;

	
	@Override
	public Long createNewCartId() {
		LOGGER.info("User counter="+counter);
		if(counter.incrementAndGet()<=1){
			//Initialization of Products There are 3 types are available.
			productDaoImpl.addProductInventory( "Product1", new TaxCategory("A", 10.0), 20);
			productDaoImpl.addProductInventory( "Product2", new TaxCategory("B", 20.0), 10);
			productDaoImpl.addProductInventory( "Product3", new TaxCategory("C", 0.0), 50);
		}
		return billSummaryDaoImpl.createNewCartId();	
	}

	/* (non-Javadoc)
	 * @see com.checkoutcounter.domain.service.IShoppingCartService#fetchCartDetails(java.lang.Integer)
	 */
	@Override
	public BillSumary fetchCartDetails(Long billId) {
		return billSummaryDaoImpl.fetchCartDetails(billId);
	}

	/* (non-Javadoc)
	 * @see com.checkoutcounter.domain.service.IShoppingCartService#updateCart(java.lang.Integer, com.checkoutcounter.domain.Product)
	 */
	@Override
	public void updateCart(Long billId, IProduct prod) {
		BillSumary summary = fetchCartDetails(billId);
		summary.addProduct(prod);
		billSummaryDaoImpl.updateCart(summary);
	}

	
	/* (non-Javadoc)
	 * @see com.checkoutcounter.domain.service.IShoppingCartService#addProductToCart(java.lang.Integer, com.checkoutcounter.domain.Product)
	 */
	@Override
	public void addProductToCart(Long billId, Integer prodId, Integer qty) {
		BillSumary summary = fetchCartDetails(billId);
		if(BillStatus.COMPLETED.equals(summary.getBillStatus()))
		{
			throw new RuntimeException("Bill is already completed you cannot add products to completed Bill");
		}
		ProductMaster prodMaster = productDaoImpl.fetchProductMaster(prodId);
		if(prodMaster == null || prodMaster.getProdId()==null){
			throw new RuntimeException("Product does not available in Inventory");
		}
		summary.addProductMaster(prodMaster, qty);
		
		billSummaryDaoImpl.updateCart(summary);
	}

	/* (non-Javadoc)
	 * @see com.checkoutcounter.domain.service.IShoppingCartService#checkoutCart(java.lang.Integer)
	 */
	@Override
	public BillSumary checkoutCart(Long billId) {
		BillSumary summary = billSummaryDaoImpl.checkoutCart(billId);
		return summary;
	}

	
	
	
}
