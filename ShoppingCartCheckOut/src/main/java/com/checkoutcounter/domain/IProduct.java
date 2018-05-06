/**
 * 
 */
package com.checkoutcounter.domain;

/**
 * @author psanghan
 *
 */
public interface IProduct {

	public ProductMaster getProduct();
	public Double getTotalPrice();
	public Double getTaxAmt();
	public Integer getProdQty();
	public void setProdQty(Integer qty);
	/*public void setProduct(ProductMaster prodMaster);
	public Double getTotalPrice();
	public Double getTotalTax();
	public Integer getProdQty();*/
	
}
