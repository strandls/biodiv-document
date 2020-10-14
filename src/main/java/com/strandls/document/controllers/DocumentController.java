/**
 * 
 */
package com.strandls.document.controllers;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.document.ApiConstants;
import com.strandls.document.es.util.ESUtility;
import com.strandls.document.pojo.BibFieldsData;
import com.strandls.document.pojo.BibTexItemType;
import com.strandls.document.pojo.BulkUploadExcelData;
import com.strandls.document.pojo.DocumentCreateData;
import com.strandls.document.pojo.DocumentEditData;
import com.strandls.document.pojo.DocumentListData;
import com.strandls.document.pojo.DocumentUserPermission;
import com.strandls.document.pojo.DownloadLogData;
import com.strandls.document.pojo.ShowDocument;
import com.strandls.document.service.DocumentService;
import com.strandls.esmodule.pojo.MapSearchParams;
import com.strandls.esmodule.pojo.MapSearchQuery;
import com.strandls.esmodule.pojo.MapSearchParams.SortTypeEnum;
import com.strandls.taxonomy.pojo.SpeciesGroup;
import com.strandls.user.pojo.Follow;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.FeaturedCreate;
import com.strandls.userGroup.pojo.UserGroupIbp;
import com.strandls.utility.pojo.FlagIbp;
import com.strandls.utility.pojo.FlagShow;
import com.strandls.utility.pojo.Habitat;
import com.strandls.utility.pojo.Language;
import com.strandls.utility.pojo.Tags;
import com.strandls.utility.pojo.TagsMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Abhishek Rudra
 *
 */

@Api("Document Service")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class DocumentController {

	@Inject
	private DocumentService docService;

	@Inject
	private ESUtility esUtility;

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getPong() {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@GET
	@Path(ApiConstants.SHOW + "/{documentId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "fetch the document show page data", notes = "returns the document show page data", response = ShowDocument.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })

	public Response showDocument(@PathParam("documentId") String documentId) {
		try {
			Long docId = Long.parseLong(documentId);
			ShowDocument result = docService.show(docId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	public Response createDocument(@Context HttpServletRequest request,
			@ApiParam(name = "documentCreate") DocumentCreateData documentCreate) {
		try {
			ShowDocument result = docService.createDocument(request, documentCreate);
			if (result != null)
				return Response.status(Status.OK).entity(result).build();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path(ApiConstants.EDIT + "/{documentId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	public Response getEditDocument(@Context HttpServletRequest request, @PathParam("documentId") String documentId) {
		try {
			Long docId = Long.parseLong(documentId);
			DocumentEditData result = docService.getDocumentEditData(request, docId);
			if (result != null)
				return Response.status(Status.OK).entity(result).build();
			return Response.status(Status.NOT_ACCEPTABLE).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.UPLOAD + ApiConstants.BIB)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)

	public Response uploadBib(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {
			BibFieldsData result = docService.readBibTex(uploadedInputStream, fileDetail);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.BULK + ApiConstants.UPLOAD + ApiConstants.BIB)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	public Response bulkUploadBib(@Context HttpServletRequest request,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		try {

			return null;
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.BULK + ApiConstants.UPLOAD + ApiConstants.EXCEL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	public Response bulkUploadExcel(@Context HttpServletRequest request,
			@ApiParam(name = "bulkUploadData") BulkUploadExcelData bulkUploadData) {
		try {
			docService.bulkUploadExcel(request, bulkUploadData);

			return Response.status(Status.OK).entity("process completed").build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.BIB + ApiConstants.ITEM + ApiConstants.ALL)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "fetch all the bibtex item type", notes = "return all the item type with id", response = BibTexItemType.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "unable to fetch the bib item type", response = String.class) })

	public Response getAllBibItemType() {
		try {
			List<BibTexItemType> result = docService.fetchAllItemType();
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.BIB + ApiConstants.FIELDS + "/{itemId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "fetch all the fields based on item type", notes = "returns all the relevant field based on item type", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })

	public Response getItemsFieldType(@PathParam("itemId") String itemId) {
		try {
			Long itemTypeId = Long.parseLong(itemId);
			Map<String, Boolean> result = docService.getAllFieldTypes(itemTypeId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path(ApiConstants.DELETE + "/{documentId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Deletet the document", notes = "Confirms if the document got deleted", response = Boolean.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "unable to delete the document", response = String.class) })

	public Response deleteDocument(@Context HttpServletRequest request, @PathParam("documentId") String documentId) {
		try {
			Long docId = Long.parseLong(documentId);
			Boolean result = docService.removeDocument(request, docId);
			if (result)
				return Response.status(Status.OK).entity(result).build();
			return Response.status(Status.NOT_ACCEPTABLE).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.PERMISSION + "/{documentId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "fetch user permission on document show page", notes = "returns the document show page permission", response = DocumentUserPermission.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "unable to fetch user permission", response = String.class) })

	public Response getUserPermission(@Context HttpServletRequest request, @PathParam("documentId") String documentId) {
		try {
			DocumentUserPermission result = docService.getUserPermission(request, documentId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.USERGROUP + ApiConstants.PERMISSION)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "fetch all the usergroup associated with a user", notes = "returns usergroup list associated with user", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "unable to fetch the usergroup", response = String.class) })

	public Response getAllowedUserGroup(@Context HttpServletRequest request) {
		try {
			List<UserGroupIbp> result = docService.getAllowedUserGroupList(request);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.ADD + ApiConstants.COMMENT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Adds a comment", notes = "Return the current activity", response = Activity.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to log a comment", response = String.class) })

	public Response addCommnet(@Context HttpServletRequest request,
			@ApiParam(name = "commentData") CommentLoggingData commentData) {
		try {
			Activity result = docService.addDocumentCommet(request, commentData);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path(ApiConstants.LANGUAGE)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find all the Languages based on IsDirty field", notes = "Returns all the Languages Details", response = Language.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Languages Not Found", response = String.class) })

	public Response getLanguaes(@QueryParam("isDirty") Boolean isDirty) {
		try {
			List<Language> result = docService.getLanguages(isDirty);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.TAGS + ApiConstants.AUTOCOMPLETE)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Find the Sugguestion for tags", notes = "Return list of Top 10 tags matching the phrase", response = Tags.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to fetch the tags", response = String.class) })

	public Response getTagsSuggetion(@QueryParam("phrase") String phrase) {
		try {
			List<Tags> result = docService.getTagsSuggestion(phrase);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.TAGS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "update tags for the Document", notes = "Returns Tags list", response = Tags.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to update the tags", response = String.class) })

	public Response updateTags(@Context HttpServletRequest request,
			@ApiParam(name = "tagsMapping") TagsMapping tagsMapping) {
		try {
			List<Tags> result = docService.updateTags(request, tagsMapping);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.SPECIESGROUP + ApiConstants.ALL)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Get all the Specie Group", notes = "Returns all the Species Group", response = SpeciesGroup.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to fetch the UserGroup", response = String.class) })

	public Response getAllSpecies() {
		try {
			List<SpeciesGroup> result = docService.getAllSpeciesGroup();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.SPECIESGROUP + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "update the speciesGroup ids", notes = "return list of speciesGroup ids", response = Long.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to update", response = String.class) })

	public Response udpateSpeciesGroup(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@ApiParam("speciesGroupList") List<Long> speciesGroupList) {
		try {
			Long docId = Long.parseLong(documentId);
			List<Long> result = docService.updateSpeciesGroup(request, docId, speciesGroupList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.HABITAT + ApiConstants.ALL)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Get all the Habitat", notes = "Returns all the habitat in habitat order", response = Habitat.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to get the habitat", response = String.class) })

	public Response getAllHabitat() {
		try {
			List<Habitat> result = docService.getAllHabitat();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.HABITAT + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "update the habitat ids", notes = "return list of habitat ids", response = Long.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to update", response = String.class) })

	public Response updateHabitat(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@ApiParam("habitatList") List<Long> habitatList) {
		try {
			Long docId = Long.parseLong(documentId);
			List<Long> result = docService.updateHabitat(request, docId, habitatList);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.USERGROUP + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Update the UserGroup linked with a Document", notes = "Returns all the current userGroup Linked", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to updated the userGroup of Document", response = String.class) })

	public Response updateUserGroup(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@ApiParam(name = "userGroupList") List<Long> userGroupList) {
		try {
			List<UserGroupIbp> result = docService.updateUserGroup(request, documentId, userGroupList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.FEATURED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Posting of Featured to a Group", notes = "Returns the Details of Featured", response = Featured.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Unable to Feature in a Group", response = String.class) })

	public Response createFeatured(@Context HttpServletRequest request,
			@ApiParam(name = "featuredCreate") FeaturedCreate featuredCreate) {
		try {
			List<Featured> result = docService.createFeatured(request, featuredCreate);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UNFEATURED + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "UnFeatures a Object from a UserGroup", notes = "Returns the Current Featured", response = Featured.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Unable to Unfeature", response = String.class) })

	public Response unFeatured(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@ApiParam(name = "userGroupList") List<Long> userGroupList) {
		try {
			List<Featured> result = docService.unFeatured(request, documentId, userGroupList);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.FLAG + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Flag a Document", notes = "Return a list of flag to the document", response = FlagShow.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to flag a Document", response = String.class),
			@ApiResponse(code = 406, message = "User has already flagged", response = String.class) })

	public Response createFlag(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@ApiParam(name = "flagIbp") FlagIbp flagIbp) {
		try {
			Long docId = Long.parseLong(documentId);
			List<FlagShow> result = docService.createFlag(request, docId, flagIbp);
			if (result.isEmpty())
				return Response.status(Status.NOT_ACCEPTABLE).entity("User Allowed Flagged").build();
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path(ApiConstants.UNFLAG + "/{documentId}/{flagId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Unflag a document", notes = "Return a list of flag to the Document", response = FlagShow.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to unflag a document", response = String.class),
			@ApiResponse(code = 406, message = "User is not allowed to unflag", response = String.class) })

	public Response unFlag(@Context HttpServletRequest request, @PathParam("documentId") String documentId,
			@PathParam("flagId") String flagId) {
		try {
			Long docId = Long.parseLong(documentId);
			List<FlagShow> result = docService.unFlag(request, docId, flagId);
			if (result == null)
				return Response.status(Status.NOT_ACCEPTABLE).entity("User not allowed to Unflag").build();
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.FOLLOW + "/{documentId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Marks follow for a User", notes = "Returnt the follow details", response = Follow.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to mark follow", response = String.class) })

	public Response followObservation(@Context HttpServletRequest request, @PathParam("documentId") String documentId) {
		try {
			Long docId = Long.parseLong(documentId);
			Follow result = docService.followRequest(request, docId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.UNFOLLOW + "/{documentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	@ApiOperation(value = "Marks unfollow for a User", notes = "Returnt the unfollow details", response = Follow.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to mark unfollow", response = String.class) })

	public Response unfollow(@Context HttpServletRequest request, @PathParam("documentId") String documentId) {

		try {
			Long docId = Long.parseLong(documentId);
			Follow result = docService.unFollowRequest(request, docId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.LOG + ApiConstants.DOWNLOAD)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	@ValidateUser

	@ApiOperation(value = "log the document download", notes = "return true incase of logging", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to log the download", response = String.class) })
	public Response logDocumentDownload(@Context HttpServletRequest request,
			@ApiParam("documentDownloadData") DownloadLogData downloadLogData) {
		try {
			Boolean result = docService.documentDownloadLog(request, downloadLogData);
			if (result != null && result)
				return Response.status(Status.OK).entity("Download logged").build();
			return Response.status(Status.NOT_ACCEPTABLE).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(ApiConstants.LIST + "/{index}/{type}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)

	public Response DocumentList(@PathParam("index") String index, @PathParam("type") String type,
			@DefaultValue("10") @QueryParam("max") Integer max, @DefaultValue("0") @QueryParam("offset") Integer offset,
			@DefaultValue("list") @QueryParam("view") String view,
			@DefaultValue("document.last_revised") @QueryParam("sort") String sortOn, @QueryParam("tags") String tags,
			@QueryParam("createdOnMaxDate") String createdOnMaxDate,
			@QueryParam("createdOnMinDate") String createdOnMinDate,
			@QueryParam("revisedOnMaxDate") String revisedOnMaxDate,
			@QueryParam("revisedOnMinDate") String revisedOnMinDate,
			@DefaultValue("") @QueryParam("isFlagged") String isFlagged,
			@DefaultValue("") @QueryParam("user") String user, @DefaultValue("") @QueryParam("sGroup") String sGroup,
			@DefaultValue("") @QueryParam("habitatIds") String habitatIds,
			@DefaultValue("") @QueryParam("flags") String flags,
			@DefaultValue("") @QueryParam("featured") String featured,
			@DefaultValue("") @QueryParam("userGroupList") String userGroupList) {
		try {

			if (max > 50) {
				max = 50;
			}

			MapSearchParams mapSearchParams = new MapSearchParams();
			mapSearchParams.setFrom(offset);
			mapSearchParams.setLimit(max);
			mapSearchParams.setSortOn(sortOn);
			mapSearchParams.setSortType(SortTypeEnum.DESC);

			MapSearchQuery mapSearchQuery = esUtility.getMapSearchQuery(sGroup, habitatIds, tags, user,
					flags, createdOnMaxDate,createdOnMinDate, featured, userGroupList, isFlagged,revisedOnMaxDate,revisedOnMinDate,mapSearchParams);
			DocumentListData result = docService.getDocumentList(index, type, mapSearchQuery);

			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

}
