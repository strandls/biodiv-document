/**
 * 
 */
package com.strandls.document.pojo;

/**
 * @author Abhishek Rudra
 *
 */
public class BibTexFieldMappingShow {

	private String fieldType;
	private Boolean isRequired;

	/**
	 * 
	 */
	public BibTexFieldMappingShow() {
		super();
	}

	/**
	 * @param fieldType
	 * @param isRequired
	 */
	public BibTexFieldMappingShow(String fieldType, Boolean isRequired) {
		super();
		this.fieldType = fieldType;
		this.isRequired = isRequired;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

}
