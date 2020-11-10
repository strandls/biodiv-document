package com.strandls.document.es.util;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.MapDocument;
import com.strandls.esmodule.pojo.MapQueryResponse;

public class ESUpdate {

	private final Logger logger = LoggerFactory.getLogger(ESUpdate.class);

	@Inject
	private EsServicesApi esService;

	@Inject
	private ObjectMapper objectMapper;

	public void updateESInstance(String documentData) {
		try {
			System.out.println("--------------------document es Update---------");
			System.out.println();
			System.out.println("------started----------");
			ShowDocument result = objectMapper.readValue(documentData, ShowDocument.class);
			String documentId = result.getDocument().getId().toString();
			System.out.println("Document getting UPDATED to elastic,ID:" + documentId);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			objectMapper.setDateFormat(df);
			String resultString = objectMapper.writeValueAsString(result);
			MapDocument doc = new MapDocument();
			doc.setDocument(resultString);
			MapQueryResponse response = esService.create(DocumentIndex.index.getValue(), DocumentIndex.type.getValue(),
					documentId, doc);
			System.out.println();
			System.out.println();
			System.out.println("-----------updated----------");
			System.out.println(response.getResult());
			System.out.println("--------------completed-------------documentId :" + documentId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
