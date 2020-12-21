package com.strandls.document.pojo;

import java.util.List;


/**
 * 
 * @author vishnu
 *
 */

public class DocumentListData {
	
	private List<DocumentMappingList> documentList;
	
	/**
	 * 
	 * @param documentList
	 * @param totalCount
	 */
	
	public DocumentListData(List<DocumentMappingList> documentList) {
		super();
		this.documentList = documentList;
	}
	
	
	public List<DocumentMappingList> getDocumentList() {
		return documentList;
	}
	
	public void setDocumentList(List<DocumentMappingList> documentList) {
		this.documentList = documentList;
	}
	
}
