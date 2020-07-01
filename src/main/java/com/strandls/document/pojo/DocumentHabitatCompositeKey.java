/**
 * 
 */
package com.strandls.document.pojo;

import java.io.Serializable;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentHabitatCompositeKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4015739698251461503L;
	private Long documentId;
	private Long habitatId;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getHabitatId() {
		return habitatId;
	}

	public void setHabitatId(Long habitatId) {
		this.habitatId = habitatId;
	}

}
