/**
 * 
 */
package com.strandls.document.pojo;

import java.util.List;

import com.strandls.userGroup.pojo.UserGroupIbp;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentUserPermission {

	private List<UserGroupIbp> userGroupMember;
	private List<UserGroupIbp> userGroupFeature;
	private Boolean following;

	/**
	 * 
	 */
	public DocumentUserPermission() {
		super();
	}

	/**
	 * @param userGroupMember
	 * @param userGroupFeature
	 * @param following
	 */
	public DocumentUserPermission(List<UserGroupIbp> userGroupMember, List<UserGroupIbp> userGroupFeature,
			Boolean following) {
		super();
		this.userGroupMember = userGroupMember;
		this.userGroupFeature = userGroupFeature;
		this.following = following;
	}

	public List<UserGroupIbp> getUserGroupMember() {
		return userGroupMember;
	}

	public void setUserGroupMember(List<UserGroupIbp> userGroupMember) {
		this.userGroupMember = userGroupMember;
	}

	public List<UserGroupIbp> getUserGroupFeature() {
		return userGroupFeature;
	}

	public void setUserGroupFeature(List<UserGroupIbp> userGroupFeature) {
		this.userGroupFeature = userGroupFeature;
	}

	public Boolean getFollowing() {
		return following;
	}

	public void setFollowing(Boolean following) {
		this.following = following;
	}

}
