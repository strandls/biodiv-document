/**
 * 
 */
package com.strandls.document.service;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.ShowDocument;

/**
 * @author Abhishek Rudra
 *
 */
public interface DocumentService {

	public ShowDocument show(Long documentId);

	public ShowDocument createDocument(HttpServletRequest request, DocumentCreateData documentCreateData);

	public Map<String, String> readBibTex(InputStream uploadedInputStream, FormDataContentDisposition fileDetail);
}
