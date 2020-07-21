/**
 * 
 */
package com.strandls.document.pojo;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentCoverageData {

	private String placename;
	private String topology;

	/**
	 * 
	 */
	public DocumentCoverageData() {
		super();
	}

	/**
	 * @param placename
	 * @param topology
	 */
	public DocumentCoverageData(String placename, String topology) {
		super();
		this.placename = placename;
		this.topology = topology;
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

}
