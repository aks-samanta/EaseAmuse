package com.EaseAmuse.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	String fieldValue;

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String resourceName, String feildName, String fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, feildName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = feildName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFeildName() {
		return fieldName;
	}

	public void setFeildName(String feildName) {
		this.fieldName = feildName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
