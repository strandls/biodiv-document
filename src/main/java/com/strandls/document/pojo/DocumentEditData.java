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

	private Long documentId;
	private Long itemTypeId;
	private Document document;
	private BibFieldsData bibFieldsData;
	private List<DocumentCoverageData> docCoverage;
	private UFileCreateData ufileData;

	/**
	 * 
	 */
	public DocumentEditData() {
		super();
	}

	/**
	 * @param documentId
	 * @param itemTypeId
	 * @param document
	 * @param bibFieldsData
	 * @param docCoverage
	 * @param ufileData
	 */
	public DocumentEditData(Long documentId, Long itemTypeId, Document document, BibFieldsData bibFieldsData,
			List<DocumentCoverageData> docCoverage, UFileCreateData ufileData) {
		super();
		this.documentId = documentId;
		this.itemTypeId = itemTypeId;
		this.document = document;
		this.bibFieldsData = bibFieldsData;
		this.docCoverage = docCoverage;
		this.ufileData = ufileData;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public BibFieldsData getBibFieldsData() {
		return bibFieldsData;
	}

	public void setBibFieldsData(BibFieldsData bibFieldsData) {
		this.bibFieldsData = bibFieldsData;
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
