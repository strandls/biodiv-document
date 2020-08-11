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
 * @author ashish
 *
 */
//1 - can be null
@Entity
@Table(name = "doc_sci_name")
public class DocSciName implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -537891833036206299L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	@Column(name = "id" , nullable = false)
	private Long id; 
	
	@Column(name = "version", columnDefinition = "integer default 2", nullable = false)
	private Long version;
	
	@Column(name = "canonical_form")
	private String canonicalForm;//1
	
	@Column(name = "display_order",nullable = false)
	private Integer displayOrder; //"display_order"
	
	@Column(name = "document_id", nullable = false)
	private Long documentId;//"document_id"
	
	@Column(name="frequency", nullable = false)
	private Integer frequency;//"frequency"
	
	@Column(name = "offset_values",nullable = false)
	private String offsetValues;//"offset_values"
	
	@Column(name = "scientific_name",nullable = false)
	private String scientificName;//"scientific_name"
	
	@Column(name = "taxon_id")
	private Long taxonId;//"taxon_concept_id" //1
	
	@Column(name = "primary_name",nullable = false)
	private String primaryName;//primary_name"
	
	@Column(name = "is_deleted",nullable = false, columnDefinition = "boolean")
	private Boolean isDeleted;//"is_deleted"

	public DocSciName() {
		super();
	}

	public DocSciName(Long version, String canonicalForm, Integer displayOrder, Long documentId,
			Integer frequency, String offsetValues, String scientificName, Long taxonId, String primaryName,
			Boolean isDeleted) {
		super();
		this.version = version;
		this.canonicalForm = canonicalForm;
		this.displayOrder = displayOrder;
		this.documentId = documentId;
		this.frequency = frequency;
		this.offsetValues = offsetValues;
		this.scientificName = scientificName;
		this.taxonId = taxonId;
		this.primaryName = primaryName;
		this.isDeleted = isDeleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCanonicalForm() {
		return canonicalForm;
	}

	public void setCanonicalForm(String canonicalForm) {
		this.canonicalForm = canonicalForm;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getOffsetValues() {
		return offsetValues;
	}

	public void setOffsetValues(String offsetValues) {
		this.offsetValues = offsetValues;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public Long getTaxonId() {
		return taxonId;
	}

	public void setTaxonId(Long taxonId) {
		this.taxonId = taxonId;
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
