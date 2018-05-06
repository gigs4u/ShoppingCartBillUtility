
package com.checkoutcounter.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "prodList",
    "billId",
    "billDate",
    "totalBillAmount",
    "totalBillTaxAmount",
    "totalBillAmountWithTax"
})

public class BillSumary {

    @JsonProperty("prodList")
    private Set<IProduct> prodList = new HashSet<IProduct>();
    @JsonProperty("billId")
    private Long billId;
    @JsonProperty("billDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//@HH:mm:ss.SSSZ
    private Date billDate;
    @JsonProperty("totalBillAmount")
    private Double totalBillAmount=0.0;
    @JsonProperty("totalBillTaxAmount")
    private Double totalBillTaxAmount=0.0;
    @JsonProperty("totalBillAmountWithTax")
    private Double totalBillAmountWithTax;
    
    private BillStatus billStatus = BillStatus.CREATED; //Default is InProgress
        
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("prodList")
    public Collection<IProduct> getProdList() {
    	return this.prodList;
        //return Collections.unmodifiableCollection(prodList);
    }

    @JsonProperty("prodList")
    public void setProdList(Set<IProduct> prodList) {
        this.prodList = prodList;
    }

    @JsonProperty("billId")
    public Long getBillId() {
        return billId;
    }

    @JsonProperty("billId")
    public void setBillId(Long billId) {
        this.billId = billId;
    }

    @JsonProperty("billDate")
    public Date getBillDate() {
        return billDate;
    }

    @JsonProperty("billDate")
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @JsonProperty("totalBillAmount")
    public Double getTotalBillAmount() {
    	this.totalBillAmount=0.0;
    	for (IProduct iProduct : prodList) {
			this.totalBillAmount=this.totalBillAmount+iProduct.getTotalPrice();
		}
        return totalBillAmount;
    }

/*    @JsonProperty("totalBillAmount")
    public void setTotalBillAmount(Double totalAmount) {
        this.totalBillAmount = totalAmount;
    }
*/
    @JsonProperty("totalBillTaxAmount")
    public Double getTotalBillTaxAmount() {
    	this.totalBillTaxAmount=0.0;
    	for (IProduct iProduct : prodList) {
			this.totalBillTaxAmount=this.totalBillTaxAmount+iProduct.getTaxAmt();
		}

        return totalBillTaxAmount;
    }

   /* @JsonProperty("totalBillTaxAmount")
    public void setTotalBillTaxAmount(Double totalTaxAmount) {
        this.totalBillTaxAmount = totalTaxAmount;
    }*/

    @JsonProperty("totalBillAmountWithTax")
    public Double getTotalBillAmountWithTax() {
    	this.totalBillAmountWithTax=this.totalBillAmount+this.totalBillTaxAmount;
        return totalBillAmountWithTax;
    }
/*
    @JsonProperty("totalBillAmountWithTax")
    public void setTotalBilAmountlWithTax(Double totalBillWithTax) {
        this.totalBillAmountWithTax = totalBillWithTax;
    }*/
    
    public void addProduct(IProduct e){
    	this.prodList.add(e);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    public enum BillStatus{
    	CREATED,
    	INPROGRESS,
    	COMPLETED
    }

	public BillStatus getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(BillStatus billStatus) {
		this.billStatus = billStatus;
	}

	public void addProductMaster(ProductMaster prodMaster, Integer qty){
		this.setBillStatus(BillStatus.INPROGRESS);
		
		IProduct product = new Product(prodMaster, qty);
		
		if(this.prodList.contains(product)){
			for (IProduct iProduct : prodList) {
				if(iProduct.equals(product)){
					iProduct.setProdQty(iProduct.getProdQty()+qty); 
				}
			}
		}else{
			this.addProduct(product);
			
		}
	}
	
}
