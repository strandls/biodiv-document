package com.strandls.document.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.strandls.resource.pojo.UFile;
//import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.pojo.Featured;
import com.strandls.userGroup.pojo.UserGroupIbp;
import com.strandls.utility.pojo.FlagShow;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentMappingList {

	
	private Document document;
	private List<DocumentCoverageMapping> documentCoverages;
	private List<UserGroupIbp> userGroupIbp;
	private List<Featured> featured;
	private UFile uFile;
	private List<Long> habitatIds;
	private List<Long> speciesGroupIds;
	private List<FlagShow> flag;
	private List<Object> tags;
	
	
	/**
	 * 
	 */
	public DocumentMappingList() {
		super();
	}

	/**
	 * @param document
	 * @param userIbp
	 * @param documentCoverages
	 * @param userGroupIbp
	 * @param featured
	 * @param uFile
	 * @param habitatIds
	 * @param speciesGroupIds
	 * @param flag
	 * @param tags
	 */
	public DocumentMappingList(Document document, List<DocumentCoverageMapping> documentCoverages,
			List<UserGroupIbp> userGroupIbp, List<Featured> featured, UFile uFile, List<Long> habitatIds,
			List<Long> speciesGroupIds, List<FlagShow> flag, List<Object> tags) {
		super();
		this.document = document;
		
		this.documentCoverages = documentCoverages;
		this.userGroupIbp = userGroupIbp;
		this.featured = featured;
		this.uFile = uFile;
		this.habitatIds = habitatIds;
		this.speciesGroupIds = speciesGroupIds;
		this.flag = flag;
		this.tags = tags;
	}

	public Object getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	
	public List<DocumentCoverageMapping> getDocumentCoverages() {
		return documentCoverages;
	}

	public void setDocumentCoverages(List<DocumentCoverageMapping> documentCoverages) {
		this.documentCoverages = documentCoverages;
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

	public Object getuFile() {
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

	public List<FlagShow> getFlag() {
		return flag;
	}

	public void setFlag(List<FlagShow> flag) {
		this.flag = flag;
	}

	public List<Object> getTags() {
		return tags;
	}

	public void setTags(List<Object> tags) {
		this.tags = tags;
	}

}
