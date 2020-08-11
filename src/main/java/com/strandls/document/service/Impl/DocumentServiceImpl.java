/**
 * 
 */
package com.strandls.document.service.Impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.document.dao.DocSciNameDao;
import com.strandls.document.dao.DocumentDao;
import com.strandls.document.dao.DocumentHabitatDao;
import com.strandls.document.dao.DocumentSpeciesGroupDao;
import com.strandls.document.pojo.Document;
import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.DocumentHabitat;
import com.strandls.document.pojo.DocumentSpeciesGroup;
import com.strandls.document.pojo.GNFinderResponseMap;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.document.util.MicroServicesUtils;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.resource.pojo.UFile;
import com.strandls.resource.pojo.UFileCreateData;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.UserGroupDocCreateData;
import com.strandls.userGroup.pojo.UserGroupIbp;
import com.strandls.utility.controller.UtilityServiceApi;
import com.strandls.utility.pojo.FlagShow;
import com.strandls.utility.pojo.Tags;
import com.strandls.utility.pojo.TagsMapping;
import com.strandls.utility.pojo.TagsMappingData;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentServiceImpl implements DocumentService {

	private final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Inject
	private DocumentDao documentDao;

	@Inject
	private DocumentHabitatDao docHabitatDao;

	@Inject
	private DocumentSpeciesGroupDao docSGroupDao;

	@Inject
	private UserServiceApi userService;

	@Inject
	private UserGroupSerivceApi ugService;

	@Inject
	private UtilityServiceApi utilityService;

	@Inject
	private ResourceServicesApi resourceService;
	
	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private DocSciNameDao docSciNameDao;
	
	@Inject 
	private EsServicesApi esServiceApi;

	@Override
	public ShowDocument show(Long documentId) {
		try {
			Document document = documentDao.findById(documentId);
			if (!document.getIsDeleted()) {

				UserIbp userIbp = userService.getUserIbp(document.getAuthorId().toString());

				List<UserGroupIbp> userGroup = ugService.getUserGroupByDocId(documentId.toString());
				List<Featured> featured = ugService.getAllFeatured("content.eml.Document", documentId.toString());

				UFile resource = null;
				if (document.getuFileId() != null)
					resource = resourceService.getUFilePath(document.getuFileId().toString());

				List<FlagShow> flag = utilityService.getFlagByObjectType("content.eml.Document", documentId.toString());
				List<Tags> tags = utilityService.getTags("document", documentId.toString());

				List<DocumentHabitat> docHabitats = docHabitatDao.findByDocumentId(documentId);
				List<DocumentSpeciesGroup> docSGroups = docSGroupDao.findByDocumentId(documentId);
				List<Long> docHabitatIds = new ArrayList<Long>();
				List<Long> docSGroupIds = new ArrayList<Long>();

				for (DocumentHabitat docHabitat : docHabitats) {
					docHabitatIds.add(docHabitat.getHabitatId());
				}
				for (DocumentSpeciesGroup docSGroup : docSGroups) {
					docSGroupIds.add(docSGroup.getSpeciesGroupId());
				}

				ShowDocument showDoc = new ShowDocument(document, userIbp, userGroup, featured, resource, docHabitatIds,
						docSGroupIds, flag, tags);
				return showDoc;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ShowDocument createDocument(HttpServletRequest request, DocumentCreateData documentCreateData) {

		try {
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long authorId = Long.parseLong(profile.getId());

			UFile ufile = null;
			if (documentCreateData.getResourceURL() != null && documentCreateData.getSize() != null) {
				UFileCreateData ufileCreateData = new UFileCreateData();
				ufileCreateData.setMimeType(documentCreateData.getMimeType());
				ufileCreateData.setPath(documentCreateData.getResourceURL());
				ufileCreateData.setSize(documentCreateData.getSize());
				ufileCreateData.setWeight(0);
				ufile = resourceService.createUFile(ufileCreateData);
			}

			Document document = new Document(null, 0L, true, documentCreateData.getAttribution(), authorId,
					documentCreateData.getContribution(), null, new Date(), documentCreateData.getDescription(), null,
					new Date(), documentCreateData.getLicenseId(), null, null, documentCreateData.getTitle(),
					documentCreateData.getType(), (ufile != null ? ufile.getId() : null),
					documentCreateData.getFromDate(), null, null, null, documentCreateData.getLatitude(), null,
					documentCreateData.getLongitude(), documentCreateData.getObservedAt(),
					documentCreateData.getReverseGeocoded(), documentCreateData.getFromDate(), null, 0, 0, 205L,
					documentCreateData.getLocationScale(), null, null, null, null, null, null, null, null, 1,
					documentCreateData.getRating(), false, null, null);

			document = documentDao.save(document);

//			speciesGroup

			for (Long speciesGroupId : documentCreateData.getSpeciesGroupIds()) {
				DocumentSpeciesGroup docSGroup = new DocumentSpeciesGroup(document.getId(), speciesGroupId);
				docSGroupDao.save(docSGroup);
			}

//			habitat 
			for (Long habitatId : documentCreateData.getHabitatIds()) {
				DocumentHabitat docHabitat = new DocumentHabitat(document.getId(), habitatId);
				docHabitatDao.save(docHabitat);
			}
//			user group
			if (documentCreateData.getUserGroupId() != null) {
				UserGroupDocCreateData groupDocCreateData = new UserGroupDocCreateData();
				groupDocCreateData.setDocumentId(document.getId());
				groupDocCreateData.setUserGroupIds(documentCreateData.getUserGroupId());
				ugService.createUGDocMapping(groupDocCreateData);
			}

//			tags
			if (documentCreateData.getTags() != null) {
				TagsMapping tagsMapping = new TagsMapping();
				tagsMapping.setObjectId(document.getId());
				tagsMapping.setTags(documentCreateData.getTags());
				TagsMappingData tagsMappingData = new TagsMappingData();
//				TODO fill in the mail data for document
				tagsMappingData.setMailData(null);
				tagsMappingData.setTagsMapping(tagsMapping);
				utilityService.createTags("document", tagsMappingData);
			}

			return show(document.getId());
		} catch (

		Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public GNFinderResponseMap parsePdfWithGNFinder(String filePath, String fileUrl, Long documentId) {
		// Accept the uploaded file differently and the urls differently
		// for uploaded file create a new txt file and parse it and send response
		// for url doe following: detect mimetype and then process based on the text/html or application/pdf
		// then sends its reponse.
		GNFinderResponseMap response = null;
		String basePath = ""; //TO-DO
		String fileNameBasedOnTimeStamp = MicroServicesUtils.getFileNameBasedOnTimeStamp();
		String gnFinderTextFileInput = basePath+fileNameBasedOnTimeStamp+"_gnfinderInput.txt"; //TODO -edit this
		
		if(filePath != null) {
			
			try {
				String pdfTextContent = MicroServicesUtils.pdfToText(filePath);
				Files.copy(new ByteArrayInputStream(pdfTextContent.getBytes()), Paths.get(gnFinderTextFileInput), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(fileUrl != null) {
			try {
				URL url = new URL(fileUrl);
				URLConnection urlConnection= url.openConnection();
				if(urlConnection.getContentType().contains("text/html")) {
					String htmlFileAbsolutePath = basePath + fileNameBasedOnTimeStamp + ".html";
					InputStream in = url.openStream();
					Files.copy(in, Paths.get(htmlFileAbsolutePath), StandardCopyOption.REPLACE_EXISTING);
					in.close();
					in = new FileInputStream(htmlFileAbsolutePath);
					ContentHandler contenthandler = new BodyContentHandler();
			        Metadata metadata = new Metadata();
			        Parser parser = new AutoDetectParser();
			        parser.parse(in, contenthandler, metadata, new ParseContext());
			        String htmlText = contenthandler.toString();
					in.close();
					Files.copy(new ByteArrayInputStream(htmlText.getBytes()), Paths.get(gnFinderTextFileInput), StandardCopyOption.REPLACE_EXISTING);
					Files.delete(Paths.get(htmlFileAbsolutePath));
				}
				else if(urlConnection.getContentType().contains("application/pdf")){
				String pdfFileAbsolutePath = basePath + fileNameBasedOnTimeStamp + ".pdf";
				InputStream in = url.openStream();
				Files.copy(in, Paths.get(pdfFileAbsolutePath), StandardCopyOption.REPLACE_EXISTING);
				String pdfTextContent = MicroServicesUtils.pdfToText(filePath);
				Files.copy(new ByteArrayInputStream(pdfTextContent.getBytes()), Paths.get(gnFinderTextFileInput), StandardCopyOption.REPLACE_EXISTING);
				in.close();
				Files.delete(Paths.get(pdfFileAbsolutePath));
				}	
			} catch (IOException e) {
				logger.error(e.getMessage());
			} catch (SAXException e) {
				logger.error(e.getMessage());
			} catch (TikaException e) {
				logger.error(e.getMessage());
			}
			
		}
		String gnfinderCommand = "gnfinder find -l eng ";
		StringBuilder stringBuilder  = new StringBuilder();
		stringBuilder.append(gnfinderCommand).append(gnFinderTextFileInput);
		BufferedReader reader = null;
		try {
			Process process;
			process = Runtime.getRuntime().exec(stringBuilder.toString());
			reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line;
			stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null){
				stringBuilder.append(line);		   
			}
			reader.close();
			response = objectMapper.readValue(String.valueOf(stringBuilder), GNFinderResponseMap.class);
			response =  MicroServicesUtils.fetchSpeciesDetails(response,esServiceApi);
			Files.delete(Paths.get(gnFinderTextFileInput));
		}
		 catch (IOException e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return response;
	}
	
	
}
