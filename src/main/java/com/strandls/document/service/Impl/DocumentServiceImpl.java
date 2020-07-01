/**
 * 
 */
package com.strandls.document.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.document.dao.DocumentDao;
import com.strandls.document.dao.DocumentHabitatDao;
import com.strandls.document.dao.DocumentSpeciesGroupDao;
import com.strandls.document.pojo.Document;
import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.DocumentHabitat;
import com.strandls.document.pojo.DocumentSpeciesGroup;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.resource.pojo.UFile;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.UserGroupIbp;

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
	private ResourceServicesApi resourceService;

	@Override
	public ShowDocument show(Long documentId) {
		try {
			Document document = documentDao.findById(documentId);

			UserIbp userIbp = userService.getUserIbp(document.getAuthorId().toString());

			List<UserGroupIbp> userGroup = ugService.getUserGroupByDocId(documentId.toString());
			List<Featured> featured = ugService.getAllFeatured("content.eml.Document", documentId.toString());

			UFile resource = null;
			if (document.getuFileId() != null)
				resource = resourceService.getUFilePath(document.getuFileId().toString());

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
					docSGroupIds);
			return showDoc;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public ShowDocument createDocument(DocumentCreateData documentCreateData) {

		return null;
	}

}
