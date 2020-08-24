/**
 * 
 */
package com.strandls.document.pojo;

import java.util.List;

import com.strandls.resource.pojo.UFileCreateData;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentEditData {

	private Document document;
	private List<DocumentCoverageData> docCoverage;
	private UFileCreateData ufileData;

	/**
	 * 
	 */
	public DocumentEditData() {
		super();
	}

	/**
	 * @param document
	 * @param docCoverage
	 * @param ufileData
	 */
	public DocumentEditData(Document document, List<DocumentCoverageData> docCoverage, UFileCreateData ufileData) {
		super();
		this.document = document;
		this.docCoverage = docCoverage;
		this.ufileData = ufileData;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<DocumentCoverageData> getDocCoverage() {
		return docCoverage;
	}

	public void setDocCoverage(List<DocumentCoverageData> docCoverage) {
		this.docCoverage = docCoverage;
	}

	public UFileCreateData getUfileData() {
		return ufileData;
	}

	public void setUfileData(UFileCreateData ufileData) {
		this.ufileData = ufileData;
	}

}
