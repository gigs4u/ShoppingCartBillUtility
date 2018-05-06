/**
 * 
 */
package com.checkoutcounter.domain.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.checkoutcounter.domain.BillSumary;
import com.checkoutcounter.domain.BillSumary.BillStatus;
import com.checkoutcounter.domain.IProduct;
import com.checkoutcounter.domain.dao.IBillSummaryDao;

/**
 * @author psanghan
 *
 */
@Repository
public class BillSummaryDaoImpl implements IBillSummaryDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BillSummaryDaoImpl.class);

	private Map<Long, BillSumary> billMap = new ConcurrentHashMap<Long, BillSumary>();
	
	
	@Override
	public Long createNewCartId() {
		final Long billId = System.currentTimeMillis();
		BillSumary summary = new BillSumary();
		summary.setBillDate(new Date());		
		summary.setBillId(billId);
		summary.setProdList(new HashSet<IProduct>());
		storeBill(billId,summary);
		return billId;
	}

	@Override
	public BillSumary fetchCartDetails(Long billId) {
		return billMap.get(billId);
	}

	@Override
	public void updateCart(BillSumary summary) {
		storeBill(summary.getBillId(), summary);
	}
	
	@Override
	public BillSumary checkoutCart(Long billId) {
		BillSumary s = fetchCartDetails(billId);
		s.setBillStatus(BillStatus.COMPLETED);
		storeBill(billId, s);
		return s;
	}
	private void storeBill(Long billId,BillSumary bill){
		billMap.put(billId, bill);
	}
	
}
