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
@Table(name = "document_species_group")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(DocumentSpeciesGroupCompositeKey.class)
public class DocumentSpeciesGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711270807388217836L;
	private Long documentId;
	private Long speciesGroupId;

	/**
	 * 
	 */
	public DocumentSpeciesGroup() {
		super();
	}

	/**
	 * @param documentId
	 * @param speciesGroupId
	 */
	public DocumentSpeciesGroup(Long documentId, Long speciesGroupId) {
		super();
		this.documentId = documentId;
		this.speciesGroupId = speciesGroupId;
	}

	@Id
	@Column(name = "document_species_groups_id")
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Id
	@Column(name = "species_group_id")
	public Long getSpeciesGroupId() {
		return speciesGroupId;
	}

	public void setSpeciesGroupId(Long speciesGroupId) {
		this.speciesGroupId = speciesGroupId;
	}

}
