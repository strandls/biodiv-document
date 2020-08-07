/**
 * 
 */
package com.strandls.document.service.Impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;
import org.jbibtex.Value;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.activity.pojo.DocumentMailData;
import com.strandls.activity.pojo.MailData;
import com.strandls.activity.pojo.UserGroupMailData;
import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.document.Headers;
import com.strandls.document.dao.BibTexFieldTypeDao;
import com.strandls.document.dao.BibTexItemFieldMappingDao;
import com.strandls.document.dao.BibTexItemTypeDao;
import com.strandls.document.dao.DocumentCoverageDao;
import com.strandls.document.dao.DocumentDao;
import com.strandls.document.dao.DocumentHabitatDao;
import com.strandls.document.dao.DocumentSpeciesGroupDao;
import com.strandls.document.pojo.BibFieldsData;
import com.strandls.document.pojo.BibTexFieldType;
import com.strandls.document.pojo.BibTexItemFieldMapping;
import com.strandls.document.pojo.BibTexItemType;
import com.strandls.document.pojo.Document;
import com.strandls.document.pojo.DocumentCoverage;
import com.strandls.document.pojo.DocumentCoverageData;
import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.DocumentHabitat;
import com.strandls.document.pojo.DocumentSpeciesGroup;
import com.strandls.document.pojo.DocumentUserPermission;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.geoentities.controllers.GeoentitiesServicesApi;
import com.strandls.geoentities.pojo.GeoentitiesWKTData;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.resource.pojo.UFile;
import com.strandls.resource.pojo.UFileCreateData;
import com.strandls.taxonomy.controllers.TaxonomyServicesApi;
import com.strandls.taxonomy.pojo.SpeciesGroup;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.Follow;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.FeaturedCreate;
import com.strandls.userGroup.pojo.FeaturedCreateData;
import com.strandls.userGroup.pojo.UserGroupDocCreateData;
import com.strandls.userGroup.pojo.UserGroupIbp;
import com.strandls.userGroup.pojo.UserGroupMappingCreateData;
import com.strandls.userGroup.pojo.UserGroupMemberRole;
import com.strandls.userGroup.pojo.UserGroupPermissions;
import com.strandls.utility.controller.UtilityServiceApi;
import com.strandls.utility.pojo.FlagCreateData;
import com.strandls.utility.pojo.FlagIbp;
import com.strandls.utility.pojo.FlagShow;
import com.strandls.utility.pojo.Habitat;
import com.strandls.utility.pojo.Language;
import com.strandls.utility.pojo.Tags;
import com.strandls.utility.pojo.TagsMapping;
import com.strandls.utility.pojo.TagsMappingData;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;

import de.undercouch.citeproc.bibtex.BibTeXConverter;
import net.minidev.json.JSONArray;

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

	@Inject
	private BibTexFieldTypeDao bibTexFieldTypeDao;

	@Inject
	private BibTexItemFieldMappingDao bibTexItemFieldMappingDao;

	@Inject
	private BibTexItemTypeDao bibTexItemTypeDao;

	@Inject
	private MailMetaDataConverter converter;

	@Inject
	private ActivitySerivceApi activityService;

	@Inject
	private TaxonomyServicesApi taxonomyService;

	@Inject
	private ObjectMapper objectMapper;

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

			BibFieldsData bibData = documentCreateData.getBibFieldData();
			Document document = new Document(null, 0L, true, documentCreateData.getAttribution(), authorId,
					documentCreateData.getContribution(), null, new Date(), bibData.getDescription(), bibData.getDoi(),
					new Date(), documentCreateData.getLicenseId(), null, null, bibData.getTitle(), bibData.getType(),
					(ufile != null ? ufile.getId() : null), documentCreateData.getFromDate(), null, null, null, null,
					null, null, null, null, documentCreateData.getFromDate(), null, 0, 0, 205L, null, null, null, null,
					null, null, null, null, null, 1, documentCreateData.getRating(), false, null, null,
					bibData.getAuthor(), bibData.getJournal(), bibData.getBooktitle(), bibData.getYear(),
					bibData.getMonth(), bibData.getVolume(), bibData.getNumber(), bibData.getPages(),
					bibData.getPublisher(), bibData.getSchool(), bibData.getEdition(), bibData.getSeries(),
					bibData.getAddress(), bibData.getChapter(), bibData.getNote(), bibData.getEditor(),
					bibData.getOrganization(), bibData.getHowpublished(), bibData.getInstitution(), bibData.getUrl(),
					bibData.getLanguage(), bibData.getFile(), bibData.getItemtype(), bibData.getIsbn(),
					bibData.getExtra());

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
			if (documentCreateData.getUserGroupId() != null && !documentCreateData.getUserGroupId().isEmpty()) {
				UserGroupDocCreateData groupDocCreateData = new UserGroupDocCreateData();
				groupDocCreateData.setDocumentId(document.getId());
				groupDocCreateData.setUserGroupIds(documentCreateData.getUserGroupId());
				ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
				ugService.createUGDocMapping(groupDocCreateData);
			}

//			tags
			if (documentCreateData.getTags() != null && !documentCreateData.getTags().isEmpty()) {
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
			if (documentCreateData.getGeoentitiesId() != null && !documentCreateData.getGeoentitiesId().isEmpty()) {
				for (Long id : documentCreateData.getGeoentitiesId()) {
					GeoentitiesWKTData geoentity = geoentitiesService.findGeoentitiesById(id.toString());
					WKTReader reader = new WKTReader(geometryFactory);
					Geometry topology = reader.read(geoentity.getWktData());
					DocumentCoverage docCoverage = new DocumentCoverage(null, document.getId(),
							geoentity.getPlaceName(), topology);
					docCoverageDao.save(docCoverage);

				}
			}
//			new wkt  coverage data
			if (documentCreateData.getDocCoverageData() != null && !documentCreateData.getDocCoverageData().isEmpty()) {
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

	@Override
	public MailData generateMailData(Long documentId) {
		try {

			MailData mailData = new MailData();
			DocumentMailData documentMailData = new DocumentMailData();
			Document document = documentDao.findById(documentId);
			documentMailData.setAuthorId(document.getAuthorId());
			documentMailData.setCreatedOn(document.getCreatedOn());
			documentMailData.setDocumentId(documentId);

			List<UserGroupIbp> userGroupIbp = ugService.getUserGroupByDocId(documentId.toString());
			List<UserGroupMailData> userGroupData = new ArrayList<UserGroupMailData>();
			for (UserGroupIbp ugIbp : userGroupIbp) {
				UserGroupMailData ugMailData = new UserGroupMailData();
				ugMailData.setId(ugIbp.getId());
				ugMailData.setIcon(ugIbp.getIcon());
				ugMailData.setName(ugIbp.getName());
				ugMailData.setWebAddress(ugIbp.getWebAddress());
				userGroupData.add(ugMailData);
			}

			mailData.setDocumentMailData(documentMailData);
			mailData.setUserGroupData(userGroupData);
			return mailData;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public Boolean removeDocument(HttpServletRequest request, Long documentId) {
		CommonProfile profile = AuthUtil.getProfileFromRequest(request);
		Long userId = Long.parseLong(profile.getId());
		JSONArray userRoles = (JSONArray) profile.getAttribute("roles");
		Document document = documentDao.findById(documentId);
		if (document.getAuthorId().equals(userId) || userRoles.contains("ROLE_ADMIN")) {
			document.setIsDeleted(true);
			documentDao.update(document);
			return true;
		}
		return false;
	}

	@Override
	public BibFieldsData readBibTex(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
		try {
			Map<String, String> bibMapping = new HashMap<String, String>();

			BibTeXDatabase bibTextDB = new BibTeXConverter().loadDatabase(uploadedInputStream);
			Map<Key, BibTeXEntry> bibTexEntires = bibTextDB.getEntries();
			for (Entry<Key, BibTeXEntry> entry : bibTexEntires.entrySet()) {
				bibMapping.put("item type", entry.getValue().getType().toString());

				for (Entry<Key, Value> bibEntry : entry.getValue().getFields().entrySet()) {
					bibMapping.put(bibEntry.getKey().toString(), bibEntry.getValue().toUserString());
				}
			}
			BibFieldsData result = objectMapper.convertValue(bibMapping, BibFieldsData.class);
			Map<String, Object> bibFieldMaps = objectMapper.convertValue(result,
					new TypeReference<Map<String, Object>>() {
					});

			for (Entry<String, Object> entry : bibFieldMaps.entrySet()) {
				bibMapping.remove(entry.getKey());
			}

			result.setItemTypeId(bibTexItemTypeDao.findByName(result.getItemtype()).getId());
			String extras = objectMapper.writeValueAsString(bibMapping);
			result.setExtra(extras);

			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Map<String, Boolean> getAllFieldTypes(Long itemTypeId) {
		List<BibTexItemFieldMapping> itemFieldMappings = bibTexItemFieldMappingDao.findByItemTypeId(itemTypeId);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		for (BibTexItemFieldMapping itemFieldMapping : itemFieldMappings) {
			BibTexFieldType fieldType = bibTexFieldTypeDao.findById(itemFieldMapping.getFieldId());
			result.put(fieldType.getFieldType(), itemFieldMapping.getIsRequired());
		}

		return result;
	}

	@Override
	public List<BibTexItemType> fetchAllItemType() {
		List<BibTexItemType> result = bibTexItemTypeDao.findAll();
		return result;
	}

	@Override
	public List<Tags> getTagsSuggestion(String phrase) {
		try {
			List<Tags> result = utilityService.getTagsAutoComplete(phrase);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Tags> updateTags(HttpServletRequest request, TagsMapping tagsMapping) {
		List<Tags> result = null;
		try {
			TagsMappingData tagsMappingData = new TagsMappingData();
			tagsMappingData.setTagsMapping(tagsMapping);
			tagsMappingData.setMailData(converter.utilityMetaData(generateMailData(tagsMapping.getObjectId())));
			utilityService = headers.addUtilityHeaders(utilityService, request.getHeader(HttpHeaders.AUTHORIZATION));
			result = utilityService.updateTags("document", tagsMappingData);
			updateDocumentLastRevised(tagsMapping.getObjectId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Activity addDocumentCommet(HttpServletRequest request, CommentLoggingData loggingData) {
		try {
			loggingData.setMailData(generateMailData(loggingData.getRootHolderId()));
			activityService = headers.addActivityHeaders(activityService, request.getHeader(HttpHeaders.AUTHORIZATION));
			Activity result = activityService.addComment("observation", loggingData);
			updateDocumentLastRevised(loggingData.getRootHolderId());
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private void updateDocumentLastRevised(Long documentId) {
		Document document = documentDao.findById(documentId);
		document.setLastRevised(new Date());
		documentDao.update(document);

//		TODO elastic update
	}

	@Override
	public List<SpeciesGroup> getAllSpeciesGroup() {
		List<SpeciesGroup> result = null;
		try {
			result = taxonomyService.getAllSpeciesGroup();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Habitat> getAllHabitat() {
		try {
			List<Habitat> result = utilityService.getAllHabitat();
			return result;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;

	}

	@Override
	public List<UserGroupIbp> updateUserGroup(HttpServletRequest request, String documentId, List<Long> userGroupList) {
		List<UserGroupIbp> result = null;
		try {

			UserGroupDocCreateData userGroupData = new UserGroupDocCreateData();
			userGroupData.setUserGroupIds(userGroupList);
			userGroupData.setMailData(converter.userGroupMetadata(generateMailData(Long.parseLong(documentId))));
			userGroupData.setDocumentId(Long.parseLong(documentId));
			ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
			result = ugService.updateUGDocMapping(userGroupData);
			updateDocumentLastRevised(Long.parseLong(documentId));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return result;
	}

	@Override
	public List<Featured> createFeatured(HttpServletRequest request, FeaturedCreate featuredCreate) {
		List<Featured> result = null;

		try {
			FeaturedCreateData featuredCreateData = new FeaturedCreateData();
			featuredCreateData.setFeaturedCreate(featuredCreate);
			featuredCreateData.setMailData(converter.userGroupMetadata(generateMailData(featuredCreate.getObjectId())));
			ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
			result = ugService.createFeatured(featuredCreateData);
			updateDocumentLastRevised(featuredCreate.getObjectId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Featured> unFeatured(HttpServletRequest request, String documentId, List<Long> userGroupList) {
		List<Featured> result = null;
		try {
			UserGroupMappingCreateData userGroupData = new UserGroupMappingCreateData();
			userGroupData.setUserGroups(userGroupList);
			userGroupData.setUgFilterData(null);
			userGroupData.setMailData(converter.userGroupMetadata(generateMailData(Long.parseLong(documentId))));
			ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
			result = ugService.unFeatured("observation", documentId, userGroupData);
			updateDocumentLastRevised(Long.parseLong(documentId));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<FlagShow> createFlag(HttpServletRequest request, Long documentId, FlagIbp flagIbp) {
		try {
			FlagCreateData flagData = new FlagCreateData();
			flagData.setFlagIbp(flagIbp);
			flagData.setMailData(converter.utilityMetaData(generateMailData(documentId)));
			utilityService = headers.addUtilityHeaders(utilityService, request.getHeader(HttpHeaders.AUTHORIZATION));
			List<FlagShow> flagList = utilityService.createFlag("document", documentId.toString(), flagData);
			int flagCount = 0;
			if (flagList != null)
				flagCount = flagList.size();

			Document document = documentDao.findById(documentId);
			document.setFlagCount(flagCount);
			document.setLastRevised(new Date());
			documentDao.update(document);

			return flagList;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public List<FlagShow> unFlag(HttpServletRequest request, Long documentId, String flagId) {

		try {

			com.strandls.utility.pojo.MailData mailData = converter.utilityMetaData(generateMailData(documentId));
			utilityService = headers.addUtilityHeaders(utilityService, request.getHeader(HttpHeaders.AUTHORIZATION));
			List<FlagShow> result = utilityService.unFlag("document", documentId.toString(), flagId, mailData);
			int flagCount = 0;
			if (result != null)
				flagCount = result.size();

			Document document = documentDao.findById(documentId);
			document.setFlagCount(flagCount);
			document.setLastRevised(new Date());
			documentDao.update(document);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;

	}

	@Override
	public Follow followRequest(HttpServletRequest request, Long documentId) {
		try {
			userService = headers.addUserHeaders(userService, request.getHeader(HttpHeaders.AUTHORIZATION));
			Follow result = userService.updateFollow("document", documentId.toString());
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Follow unFollowRequest(HttpServletRequest request, Long documentId) {
		try {
			userService = headers.addUserHeaders(userService, request.getHeader(HttpHeaders.AUTHORIZATION));
			Follow result = userService.unfollow("document", documentId.toString());
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DocumentUserPermission getUserPermission(HttpServletRequest request, String documentId) {

		DocumentUserPermission permission = new DocumentUserPermission();
		try {

			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			List<UserGroupIbp> allowedUserGroup = new ArrayList<UserGroupIbp>();
			List<Long> userGroupFeatureRole = new ArrayList<Long>();

			userService = headers.addUserHeaders(userService, request.getHeader(HttpHeaders.AUTHORIZATION));
			Follow follow = userService.getFollowByObject("document", documentId);

			List<UserGroupIbp> associatedUserGroup = ugService.getUserGroupByDocId(documentId);
			ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
			UserGroupPermissions userGroupPermission = ugService.getUserGroupPermission();

			JSONArray userRole = (JSONArray) profile.getAttribute("roles");
			if (userRole.contains("ROLE_ADMIN")) {

				allowedUserGroup = ugService.getAllUserGroup();
				for (UserGroupIbp ug : allowedUserGroup) {
					userGroupFeatureRole.add(ug.getId());
				}

			} else {

				List<Long> userGroupMember = new ArrayList<Long>();
				for (UserGroupMemberRole userMemberRole : userGroupPermission.getUserMemberRole()) {
					userGroupMember.add(userMemberRole.getUserGroupId());
				}
				String s = userGroupMember.toString();
				if (s.substring(1, s.length() - 1).trim().length() != 0)
					allowedUserGroup = ugService.getUserGroupList(s.substring(1, s.length() - 1));

				for (UserGroupMemberRole userFeatureRole : userGroupPermission.getUserFeatureRole()) {
					userGroupFeatureRole.add(userFeatureRole.getUserGroupId());
				}
			}

			List<Long> userGroupIdList = new ArrayList<Long>();
			List<UserGroupIbp> featureableGroup = new ArrayList<UserGroupIbp>();
			for (UserGroupIbp userGroup : associatedUserGroup) {
				userGroupIdList.add(userGroup.getId());
				if (userGroupFeatureRole.contains(userGroup.getId()))
					featureableGroup.add(userGroup);

			}

			permission = new DocumentUserPermission(allowedUserGroup, featureableGroup,
					(follow != null) ? true : false);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return permission;
	}

	@Override
	public List<UserGroupIbp> getAllowedUserGroupList(HttpServletRequest request) {
		try {
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			List<UserGroupIbp> allowedUserGroup = null;
			JSONArray userRole = (JSONArray) profile.getAttribute("roles");
			if (userRole.contains("ROLE_ADMIN")) {
				allowedUserGroup = ugService.getAllUserGroup();
			} else {

				ugService = headers.addUserGroupHeader(ugService, request.getHeader(HttpHeaders.AUTHORIZATION));
				UserGroupPermissions userGroupPermission = ugService.getUserGroupPermission();

				List<Long> userGroupMember = new ArrayList<Long>();
				for (UserGroupMemberRole userMemberRole : userGroupPermission.getUserMemberRole()) {
					userGroupMember.add(userMemberRole.getUserGroupId());
				}
				String s = userGroupMember.toString();
				allowedUserGroup = ugService.getUserGroupList(s.substring(1, s.length() - 1));
			}

			return allowedUserGroup;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Language> getLanguages(Boolean isDirty) {

		List<Language> result = null;
		try {
			result = utilityService.getAllLanguages(isDirty);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Long> updateSpeciesGroup(HttpServletRequest request, Long documentId, List<Long> speciesGroupList) {

		List<DocumentSpeciesGroup> previousDocSGroup = docSGroupDao.findByDocumentId(documentId);
		for (DocumentSpeciesGroup docSgroup : previousDocSGroup) {
			if (!speciesGroupList.contains(docSgroup.getSpeciesGroupId())) {
				docSGroupDao.delete(docSgroup);
			} else {
				speciesGroupList.remove(docSgroup.getSpeciesGroupId());
			}
		}

		for (Long speciesGroupId : speciesGroupList) {
			DocumentSpeciesGroup docSGroup = new DocumentSpeciesGroup(documentId, speciesGroupId);
			docSGroupDao.save(docSGroup);
		}

		List<DocumentSpeciesGroup> newDocSgroup = docSGroupDao.findByDocumentId(documentId);
		List<Long> result = new ArrayList<Long>();
		for (DocumentSpeciesGroup sGroup : newDocSgroup)
			result.add(sGroup.getSpeciesGroupId());

		return result;
	}

	@Override
	public List<Long> updateHabitat(HttpServletRequest request, Long documentId, List<Long> habitatList) {
		List<DocumentHabitat> previousHabitats = docHabitatDao.findByDocumentId(documentId);
		for (DocumentHabitat docHabitat : previousHabitats) {
			if (!habitatList.contains(docHabitat.getHabitatId())) {
				docHabitatDao.delete(docHabitat);
			} else {
				habitatList.remove(docHabitat.getHabitatId());
			}
		}

		for (Long habitatId : habitatList) {
			DocumentHabitat docHabitat = new DocumentHabitat(documentId, habitatId);
			docHabitatDao.save(docHabitat);
		}
		List<DocumentHabitat> newDocHabitat = docHabitatDao.findByDocumentId(documentId);
		List<Long> habitatId = new ArrayList<Long>();
		for (DocumentHabitat docHabitat : newDocHabitat)
			habitatId.add(docHabitat.getHabitatId());

		return habitatId;
	}

}
