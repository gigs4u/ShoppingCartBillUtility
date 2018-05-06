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
@JsonPropertyOrder({
    "categoryName",
    "taxRate"
})
//This can also be made an enum
public class TaxCategory {
	
	public TaxCategory(String categoryName,Double taxRate){
		this.categoryName=categoryName;
		this.taxRate=taxRate;
	}
	public TaxCategory(){
		
	}

	@JsonProperty("categoryName")
    private String categoryName;
    @JsonProperty("taxRate")
    private Double taxRate;
    
    @JsonProperty("categoryName")
	public String getCategoryName() {
		return categoryName;
	}
    @JsonProperty("categoryName")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    @JsonProperty("taxRate")
    public Double getTaxRate() {
		return taxRate;
	}
    @JsonProperty("taxRate")
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
}
