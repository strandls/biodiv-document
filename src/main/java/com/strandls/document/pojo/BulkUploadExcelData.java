/**
 * 
 */
package com.strandls.document.pojo;

import java.util.Map;

/**
 * @author Abhishek Rudra
 *
 */
public class BulkUploadExcelData {

	private String fileName;
	private Map<String, Integer> fieldMapping;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, Integer> getFieldMapping() {
		return fieldMapping;
	}

	public void setFieldMapping(Map<String, Integer> fieldMapping) {
		this.fieldMapping = fieldMapping;
	}

}
