/**
 * 
 */
package com.checkoutcounter.domain.service;

import com.checkoutcounter.domain.BillSumary;
import com.checkoutcounter.domain.IProduct;

/**
 * @author psanghan
 *
 */
public interface IShoppingCartService {

	
	Long createNewCartId();

	
	BillSumary fetchCartDetails(Long billId);
	
	
	void updateCart(Long billId, IProduct prod);
	
	void addProductToCart(Long billId, Integer prodId, Integer qty);
	
	
	BillSumary checkoutCart(Long billId);

	
}
