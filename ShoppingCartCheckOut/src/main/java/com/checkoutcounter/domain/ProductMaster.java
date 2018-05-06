/**
 * 
 */
package com.checkoutcounter.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author psanghan
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
		{ 
			"prodId",
			"productName",	 
			"prodPrice", 	 
			"taxCategory" 
		}
	)
public class ProductMaster {
	
	
	public ProductMaster(Integer prodId,String productName,Double prodPrice,TaxCategory taxCategory){
		this.prodId=prodId;
		this.productName=productName;
		this.prodPrice=prodPrice;
		this.taxCategory=taxCategory;
	}
	
	//Using this Product master constructor as I am reusing this for controller
	public ProductMaster(){
	}
	
	@JsonProperty("prodId")
	private Integer prodId;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("prodPrice")
	private Double prodPrice;
	@JsonProperty("taxCategory")
	private TaxCategory taxCategory;
	
	
	@JsonProperty("productName")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("productName")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonProperty("prodPrice")
	public Double getProdPrice() {
		return prodPrice;
	}

	@JsonProperty("prodPrice")
	public void setProdPrice(Double prodPrice) {
		this.prodPrice = prodPrice;
	}

	@JsonProperty("prodId")
	public Integer getProdId() {
		return prodId;
	}

	@JsonProperty("prodId")
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	@JsonProperty("taxCategory")
	public TaxCategory getTaxCategory() {
		return taxCategory;
	}

	@JsonProperty("taxCategory")
	public void setTaxCategory(TaxCategory taxCategory) {
		this.taxCategory = taxCategory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodId == null) ? 0 : prodId.hashCode());
		//result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductMaster other = (ProductMaster) obj;
		if (prodId == null) {
			if (other.prodId != null)
				return false;
		} else if (!prodId.equals(other.prodId))
			return false;
	/*	if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;*/
		return true;
	}


}
