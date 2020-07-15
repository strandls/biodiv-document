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

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

/**
 * @author Abhishek Rudra
 *
 */

@Entity
@Table(name = "document_coverage")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCoverage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9149317039031323675L;
	private Long id;
	private Long documentId;
	private String placeName;
	private Geometry topology;

	/**
	 * 
	 */
	public DocumentCoverage() {
		super();
	}

	/**
	 * @param id
	 * @param documentId
	 * @param placeName
	 * @param topology
	 */
	public DocumentCoverage(Long id, Long documentId, String placeName, Geometry topology) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.placeName = placeName;
		this.topology = topology;
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

	@Column(name = "document_id")
	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Column(name = "placename")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Column(name = "topology", columnDefinition = "Geometry", nullable = false)
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
	public Geometry getTopology() {
		return topology;
	}

	public void setTopology(Geometry topology) {
		this.topology = topology;
	}

}
