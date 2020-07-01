/**
 * 
 */
package com.strandls.document.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Abhishek Rudra
 *
 */
@Entity
@Table(name = "document_habitat")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(DocumentHabitatCompositeKey.class)
public class DocumentHabitat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943853197673084625L;
	private Long documentId;
	private Long habitatId;

	/**
	 * 
	 */
	public DocumentHabitat() {
		super();
	}

	/**
	 * @param documentId
	 * @param habitatId
	 */
	public DocumentHabitat(Long documentId, Long habitatId) {
		super();
		this.documentId = documentId;
		this.habitatId = habitatId;
	}

	@Id
	@Column(name = "document_habitats_id")
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Id
	@Column(name = "habitat_id")
	public Long getHabitatId() {
		return habitatId;
	}

	public void setHabitatId(Long habitatId) {
		this.habitatId = habitatId;
	}

}
