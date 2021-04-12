/**
 * 
 */
package com.strandls.document.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Abhishek Rudra
 *
 * 
 */

@Entity
@Table(name = "doc_sci_name")
public class DocSciName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2996462430848765907L;
	private Long id;
	private Long version;
	private String canonicalForm;
	private Integer displayOrder;
	private Long documentId;
	private Integer frequency;
	private String offsetValues;
	private String scientificName;
	private Long taxonConceptId;
	private Integer primaryName;
	private Boolean isDeleted;

	/**
	 * 
	 */
	public DocSciName() {
		super();
	}

	/**
	 * @param id
	 * @param version
	 * @param canonicalForm
	 * @param displayOrder
	 * @param documentId
	 * @param frequency
	 * @param offsetValues
	 * @param scientificName
	 * @param taxonConceptId
	 * @param primaryName
	 * @param isDeleted
	 */
	public DocSciName(Long id, Long version, String canonicalForm, Integer displayOrder, Long documentId,
			Integer frequency, String offsetValues, String scientificName, Long taxonConceptId, Integer primaryName,
			Boolean isDeleted) {
		super();
		this.id = id;
		this.version = version;
		this.canonicalForm = canonicalForm;
		this.displayOrder = displayOrder;
		this.documentId = documentId;
		this.frequency = frequency;
		this.offsetValues = offsetValues;
		this.scientificName = scientificName;
		this.taxonConceptId = taxonConceptId;
		this.primaryName = primaryName;
		this.isDeleted = isDeleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "version")
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "canonical_form")
	public String getCanonicalForm() {
		return canonicalForm;
	}

	public void setCanonicalForm(String canonicalForm) {
		this.canonicalForm = canonicalForm;
	}

	@Column(name = "display_order")
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Column(name = "document_id")
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Column(name = "frequency")
	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	@Column(name = "offset_values")
	public String getOffsetValues() {
		return offsetValues;
	}

	public void setOffsetValues(String offsetValues) {
		this.offsetValues = offsetValues;
	}

	@Column(name = "scientific_name")
	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	@Column(name = "taxon_concept_id")
	public Long getTaxonConceptId() {
		return taxonConceptId;
	}

	public void setTaxonConceptId(Long taxonConceptId) {
		this.taxonConceptId = taxonConceptId;
	}

	@Column(name = "primary_name")
	public Integer getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(Integer primaryName) {
		this.primaryName = primaryName;
	}

	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
