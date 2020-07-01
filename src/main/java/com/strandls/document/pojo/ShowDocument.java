/**
 * 
 */
package com.strandls.document.pojo;

import java.util.List;

import com.strandls.resource.pojo.UFile;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.UserGroupIbp;

/**
 * @author Abhishek Rudra
 *
 */
public class ShowDocument {

	private Document document;
	private UserIbp userIbp;
	private List<UserGroupIbp> userGroupIbp;
	private List<Featured> featured;
	private UFile uFile;
	private List<Long> habitatIds;
	private List<Long> speciesGroupIds;

	/**
	 * 
	 */
	public ShowDocument() {
		super();
	}

	/**
	 * @param document
	 * @param userIbp
	 * @param userGroupIbp
	 * @param featured
	 * @param uFile
	 * @param habitatIds
	 * @param speciesGroupIds
	 */
	public ShowDocument(Document document, UserIbp userIbp, List<UserGroupIbp> userGroupIbp, List<Featured> featured,
			UFile uFile, List<Long> habitatIds, List<Long> speciesGroupIds) {
		super();
		this.document = document;
		this.userIbp = userIbp;
		this.userGroupIbp = userGroupIbp;
		this.featured = featured;
		this.uFile = uFile;
		this.habitatIds = habitatIds;
		this.speciesGroupIds = speciesGroupIds;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public UserIbp getUserIbp() {
		return userIbp;
	}

	public void setUserIbp(UserIbp userIbp) {
		this.userIbp = userIbp;
	}

	public List<UserGroupIbp> getUserGroupIbp() {
		return userGroupIbp;
	}

	public void setUserGroupIbp(List<UserGroupIbp> userGroupIbp) {
		this.userGroupIbp = userGroupIbp;
	}

	public List<Featured> getFeatured() {
		return featured;
	}

	public void setFeatured(List<Featured> featured) {
		this.featured = featured;
	}

	public UFile getuFile() {
		return uFile;
	}

	public void setuFile(UFile uFile) {
		this.uFile = uFile;
	}

	public List<Long> getHabitatIds() {
		return habitatIds;
	}

	public void setHabitatIds(List<Long> habitatIds) {
		this.habitatIds = habitatIds;
	}

	public List<Long> getSpeciesGroupIds() {
		return speciesGroupIds;
	}

	public void setSpeciesGroupIds(List<Long> speciesGroupIds) {
		this.speciesGroupIds = speciesGroupIds;
	}

}
