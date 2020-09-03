/**
 * 
 */
package com.strandls.document.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Abhishek Rudra
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCoverageData {

	private String placename;
	private String topology;
	private Long geoEntityId;

	/**
	 * 
	 */
	public DocumentCoverageData() {
		super();
	}

	/**
	 * @param placename
	 * @param topology
	 * @param geoEntityId
	 */
	public DocumentCoverageData(String placename, String topology, Long geoEntityId) {
		super();
		this.placename = placename;
		this.topology = topology;
		this.geoEntityId = geoEntityId;
	}

	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getTopology() {
		return topology;
	}

	public void setTopology(String topology) {
		this.topology = topology;
	}

	public Long getGeoEntityId() {
		return geoEntityId;
	}

	public void setGeoEntityId(Long geoEntityId) {
		this.geoEntityId = geoEntityId;
	}

}
