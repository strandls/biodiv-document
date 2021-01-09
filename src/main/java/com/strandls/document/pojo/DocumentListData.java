package com.strandls.document.pojo;

import java.util.List;


/**
 * 
 * @author vishnu
 *
 */

public class DocumentListData {
	
	private List<DocumentMappingList> documentList;
	private long totalCount;
	
	/**
	 * 
	 * @param documentList
	 * @param totalCount
	 */
	
	public DocumentListData(List<DocumentMappingList> documentList,long totalCount) {
		super();
		this.documentList = documentList;
		this.setTotalCount(totalCount);
	}
	
	
	public List<DocumentMappingList> getDocumentList() {
		return documentList;
	}
	
	public void setDocumentList(List<DocumentMappingList> documentList) {
		this.documentList = documentList;
	}


	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
}
