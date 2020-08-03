/**
 * 
 */
package com.strandls.document;

import javax.ws.rs.core.HttpHeaders;

import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.utility.controller.UtilityServiceApi;

/**
 * @author Abhishek Rudra
 *
 */
public class Headers {

	public ActivitySerivceApi addActivityHeaders(ActivitySerivceApi activityService, String authHeader) {
		activityService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return activityService;
	}

	public ResourceServicesApi addResourceHeaders(ResourceServicesApi resourceService, String authHeader) {
		resourceService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return resourceService;
	}

	public UserGroupSerivceApi addUserGroupHeader(UserGroupSerivceApi ugService, String authHeader) {
		ugService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return ugService;
	}

	public UtilityServiceApi addUtilityHeaders(UtilityServiceApi utilityServices, String authHeader) {
		utilityServices.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return utilityServices;
	}

	public UserServiceApi addUserHeaders(UserServiceApi userService, String authHeader) {
		userService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userService;
	}

}
