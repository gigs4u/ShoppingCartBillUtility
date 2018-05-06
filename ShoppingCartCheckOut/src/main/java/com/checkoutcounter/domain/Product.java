
package com.checkoutcounter.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
		{ 
	"product", 
	"prodQty",	 
	"taxAmt", 
	"totalPrice" }
		)
public class Product implements IProduct{
	
	public Product(ProductMaster product,Integer prodQty){
		this.product = product;
		this.prodQty = prodQty;
	}

	@JsonProperty("product")
	private ProductMaster product;
	
	@JsonProperty("prodQty")
	private Integer prodQty;
	
	@JsonProperty("taxAmt")
	private Double taxAmt;
	
	@JsonProperty("totalPrice")
	private Double totalPrice;
	
	
	@JsonProperty("taxAmt")
	public Double getTaxAmt() {
		this.taxAmt = (this.getProduct().getTaxCategory().getTaxRate()/100)*this.getTotalPrice();
		return this.taxAmt;
	}

/*	@JsonProperty("totalTax")
	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}
*/
	@JsonProperty("totalPrice")
	public Double getTotalPrice() {
		this.totalPrice = this.product.getProdPrice()*this.prodQty;
		return this.totalPrice;
	}

	/*@JsonProperty("totalPrice")
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}*/

	@JsonProperty("prodQty")
	public Integer getProdQty() {
		return prodQty;
	}

	@JsonProperty("prodQty")
	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}
	@JsonProperty("product")
	public ProductMaster getProduct() {
		return product;
	}
	@JsonProperty("product")
	public void setProduct(ProductMaster product) {
		this.product = product;
	}
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Product other = (Product) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	

}
