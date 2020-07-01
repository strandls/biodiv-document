/**
 * 
 */
package com.strandls.document.service;

import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.ShowDocument;

/**
 * @author Abhishek Rudra
 *
 */
public interface DocumentService {

	public ShowDocument show(Long documentId);

	public ShowDocument createDocument(DocumentCreateData documentCreateData);

}
