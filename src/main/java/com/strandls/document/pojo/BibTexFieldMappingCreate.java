/**
 * 
 */
package com.strandls.document.pojo;

import java.util.List;

/**
 * @author Abhishek Rudra
 *
 */
public class BibTexFieldMappingCreate {

	private Long itemTypeId;
	private List<Long> requiredFields;
	private List<Long> optionalFields;

	/**
	 * 
	 */
	public BibTexFieldMappingCreate() {
		super();
	}

	/**
	 * @param itemTypeId
	 * @param requiredFields
	 * @param optionalFields
	 */
	public BibTexFieldMappingCreate(Long itemTypeId, List<Long> requiredFields, List<Long> optionalFields) {
		super();
		this.itemTypeId = itemTypeId;
		this.requiredFields = requiredFields;
		this.optionalFields = optionalFields;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public List<Long> getRequiredFields() {
		return requiredFields;
	}

	public void setRequiredFields(List<Long> requiredFields) {
		this.requiredFields = requiredFields;
	}

	public List<Long> getOptionalFields() {
		return optionalFields;
	}

	public void setOptionalFields(List<Long> optionalFields) {
		this.optionalFields = optionalFields;
	}

}
