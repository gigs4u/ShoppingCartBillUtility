/**
 * 
 */
package com.checkoutcounter.domain.dao;

import com.checkoutcounter.domain.BillSumary;

/**
 * @author psanghan
 *
 */
public interface IBillSummaryDao {
	
	Long createNewCartId();

	
	BillSumary fetchCartDetails(Long billId);
	
	
	void updateCart(BillSumary summary);
	
	
	BillSumary checkoutCart(Long billId);


}
