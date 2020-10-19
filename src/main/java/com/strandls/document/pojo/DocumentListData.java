package com.strandls.document.pojo;

import java.util.List;


/**
 * 
 * @author vishnu
 *
 */

public class DocumentListData {
	
	private List<DocumentMappingList> documentList;
//	private Long totalCount;
	
	/**
	 * 
	 * @param documentList
	 * @param totalCount
	 */
	
	public DocumentListData(List<DocumentMappingList> documentList) {
		super();
		this.documentList = documentList;
//		this.totalCount = totalCount;
	}
	
	
	public List<DocumentMappingList> getDocumentList() {
		return documentList;
	}
	
	public void setDocumentList(List<DocumentMappingList> documentList) {
		this.documentList = documentList;
	}
	
//	public Long getTotalCount() {
//		return totalCount;
//	}
	
//	public void setTotalCount(Long totalCount) {
//		this.totalCount = totalCount;
//	}
	
	

}
