/**
 * 
 */
package com.strandls.document.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.document.ApiConstants;
import com.strandls.document.pojo.DocumentCreateData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author Abhishek Rudra
 *
 */

@Api("Document Service")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class DocumentController {

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getPong() {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@POST
	@Path(ApiConstants.UPLOAD)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser

	public Response createDocument(@Context HttpServletRequest request,
			@ApiParam(name = "documentCreate") DocumentCreateData documentCreate) {
		try {
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
