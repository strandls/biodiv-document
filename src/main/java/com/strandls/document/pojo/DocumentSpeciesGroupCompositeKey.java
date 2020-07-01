/**
 * 
 */
package com.strandls.document.pojo;

import java.io.Serializable;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentSpeciesGroupCompositeKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2564541889800910331L;
	private Long documentId;
	private Long speciesGroupId;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getSpeciesGroupId() {
		return speciesGroupId;
	}

	public void setSpeciesGroupId(Long speciesGroupId) {
		this.speciesGroupId = speciesGroupId;
	}

}
