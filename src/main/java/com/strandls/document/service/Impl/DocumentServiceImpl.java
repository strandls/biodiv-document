/**
 * 
 */
package com.strandls.document.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.document.Headers;
import com.strandls.document.dao.DocumentCoverageDao;
import com.strandls.document.dao.DocumentDao;
import com.strandls.document.dao.DocumentHabitatDao;
import com.strandls.document.dao.DocumentSpeciesGroupDao;
import com.strandls.document.pojo.Document;
import com.strandls.document.pojo.DocumentCoverage;
import com.strandls.document.pojo.DocumentCoverageData;
import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.DocumentHabitat;
import com.strandls.document.pojo.DocumentSpeciesGroup;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.geoentities.controllers.GeoentitiesServicesApi;
import com.strandls.geoentities.pojo.GeoentitiesCreateData;
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
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;

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
	private DocumentCoverageDao docCoverageDao;

	@Inject
	private Headers headers;

	@Inject
	private GeometryFactory geometryFactory;

	@Inject
	private GeoentitiesServicesApi geoentitiesService;

	@Override
	public ShowDocument show(Long documentId) {
		try {
			Document document = documentDao.findById(documentId);
			if (!document.getIsDeleted()) {

				UserIbp userIbp = userService.getUserIbp(document.getAuthorId().toString());

				List<DocumentCoverage> documentCoverages = docCoverageDao.findByDocumentId(documentId);
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

				ShowDocument showDoc = new ShowDocument(document, userIbp, documentCoverages, userGroup, featured,
						resource, docHabitatIds, docSGroupIds, flag, tags);

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
				resourceService = headers.addResourceHeaders(resourceService,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				ufile = resourceService.createUFile(ufileCreateData);
			}

			Document document = new Document(null, 0L, true, documentCreateData.getAttribution(), authorId,
					documentCreateData.getContribution(), null, new Date(), documentCreateData.getDescription(), null,
					new Date(), documentCreateData.getLicenseId(), null, null, documentCreateData.getTitle(),
					documentCreateData.getType(), (ufile != null ? ufile.getId() : null),
					documentCreateData.getFromDate(), null, null, null, null, null, null, null, null,
					documentCreateData.getFromDate(), null, 0, 0, 205L, null, null, null, null, null, null, null, null,
					null, 1, documentCreateData.getRating(), false, null, null);

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
				ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
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
				utilityService = headers.addUtilityHeaders(utilityService,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				utilityService.createTags("document", tagsMappingData);
			}

//			document coverage geo entities ids 
			if (documentCreateData.getGeoentitiesId() != null) {
				for (Long id : documentCreateData.getGeoentitiesId()) {
					GeoentitiesCreateData geoentity = geoentitiesService.findGeoentitiesById(id.toString());
					WKTReader reader = new WKTReader(geometryFactory);
					Geometry topology = reader.read(geoentity.getWktData());
					DocumentCoverage docCoverage = new DocumentCoverage(null, document.getId(),
							geoentity.getPlaceName(), topology);
					docCoverageDao.save(docCoverage);

				}
			}
//			new wkt  coverage data
			if (documentCreateData.getDocCoverageData() != null) {
				for (DocumentCoverageData docCoverageData : documentCreateData.getDocCoverageData()) {
					WKTReader reader = new WKTReader(geometryFactory);
					Geometry topology = reader.read(docCoverageData.getTopology());
					DocumentCoverage docCoverage = new DocumentCoverage(null, document.getId(),
							docCoverageData.getPlacename(), topology);
					docCoverageDao.save(docCoverage);
				}
			}

			return show(document.getId());
		} catch (

		Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}
