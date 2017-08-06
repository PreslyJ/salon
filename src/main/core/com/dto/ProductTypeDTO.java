package com.dto;

public class ProductTypeDTO {
	private int productTypeId;
	private String rangeCode;
	private String rangeDescription;
	private String code;
	private String description;
	private double productAmount;
	private String status;
	
	
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getRangeCode() {
		return rangeCode;
	}
	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}
	public String getRangeDescription() {
		return rangeDescription;
	}
	public void setRangeDescription(String rangeDescription) {
		this.rangeDescription = rangeDescription;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
