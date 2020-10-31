package com.strandls.document.es.util;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.MapDocument;
import com.strandls.esmodule.pojo.MapQueryResponse;

public class ESUpdate {

	private final Logger logger = LoggerFactory.getLogger(ESUpdate.class);

	@Inject
	private DocumentService docService;

	@Inject
	private EsServicesApi esService;

	@Inject
	private ObjectMapper om;

	public void updateESInstance(String documentId) {
		try {
			System.out.println("--------------------document es Update---------");
			System.out.println();
			System.out.println("------started----------");
			System.out.println("Document getting UPDATED to elastic, ID:" + documentId);
			Long docId = Long.parseLong(documentId);
			ShowDocument result = docService.show(docId);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//			om.setDateFormat(df);
			String resultString = om.writeValueAsString(result);
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
