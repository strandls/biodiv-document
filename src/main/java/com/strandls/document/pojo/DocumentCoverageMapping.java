package com.strandls.document.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCoverageMapping {
	private Long id;
	private Long documentId;
	private Long geoEntityId;
	private String placeName;
	private Long landscapeIds;

	/**
	 * 
	 */
	public DocumentCoverageMapping() {
		super();
	}

	/**
	 * @param id
	 * @param documentId
	 * @param geoEntityId
	 * @param placeName
	 * @param topology
	 */
	public DocumentCoverageMapping(Long id, Long documentId, Long geoEntityId, String placeName, Object topology) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.geoEntityId = geoEntityId;
		this.placeName = placeName;
		}

	/**
	 * @param id
	 * @param documentId
	 * @param geoEntityId
	 * @param placeName
	 * @param topology
	 * @param landscapeIds
	 */
	public DocumentCoverageMapping(Long id, Long documentId, Long geoEntityId, String placeName,
			Long landscapeIds) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.geoEntityId = geoEntityId;
		this.placeName = placeName;
		this.landscapeIds = landscapeIds;
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

	@Column(name = "geoentity_id")
	public Long getGeoEntityId() {
		return geoEntityId;
	}

	public void setGeoEntityId(Long geoEntityId) {
		this.geoEntityId = geoEntityId;
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
	

	@Transient
	public Long getLandscapeIds() {
		return landscapeIds;
	}

	public void setLandscapeIds(Long landscapeIds) {
		this.landscapeIds = landscapeIds;
	}

}

