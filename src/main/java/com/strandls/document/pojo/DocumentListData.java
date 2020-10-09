package com.strandls.document.pojo;

import java.util.List;


/**
 * 
 * @author vishnu
 *
 */

public class DocumentListData {
	
	private List<ShowDocument> observationList;
//	private Long totalCount;
	
	/**
	 * 
	 * @param observationList
	 * @param totalCount
	 */
	
	public DocumentListData(List<ShowDocument> observationList) {
		super();
		this.observationList = observationList;
//		this.totalCount = totalCount;
	}
	
	
	public List<ShowDocument> getObservationList() {
		return observationList;
	}
	
	public void setObservationList(List<ShowDocument> observationList) {
		this.observationList = observationList;
	}
	
//	public Long getTotalCount() {
//		return totalCount;
//	}
	
//	public void setTotalCount(Long totalCount) {
//		this.totalCount = totalCount;
//	}
	
	

}
