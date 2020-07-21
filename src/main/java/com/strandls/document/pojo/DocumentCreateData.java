/**
 * 
 */
package com.strandls.document.pojo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.strandls.utility.pojo.Tags;

/**
 * @author Abhishek Rudra
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCreateData {

	private String type;
	private String title;
	private String contribution;
	private String attribution;
	private Long licenseId;
	private String description;
	private Date fromDate;
	private Integer rating;

//	-----RESOURCE DATA------
	private String mimeType;
	private String resourceURL;
	private String size;

//	-----TAGS--------
	private List<Tags> tags;

//	-----TAXONOMIC COVERAGE-------
	private List<Long> speciesGroupIds;
	private List<Long> habitatIds;

//	-----Location Data--------

	private List<Long> geoentitiesId;
	private List<DocumentCoverageData> docCoverageData;

//	-----User Group Data-----
	private List<Long> userGroupId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public List<Long> getSpeciesGroupIds() {
		return speciesGroupIds;
	}

	public void setSpeciesGroupIds(List<Long> speciesGroupIds) {
		this.speciesGroupIds = speciesGroupIds;
	}

	public List<Long> getHabitatIds() {
		return habitatIds;
	}

	public void setHabitatIds(List<Long> habitatIds) {
		this.habitatIds = habitatIds;
	}

	public List<Long> getGeoentitiesId() {
		return geoentitiesId;
	}

	public void setGeoentitiesId(List<Long> geoentitiesId) {
		this.geoentitiesId = geoentitiesId;
	}

	public List<DocumentCoverageData> getDocCoverageData() {
		return docCoverageData;
	}

	public void setDocCoverageData(List<DocumentCoverageData> docCoverageData) {
		this.docCoverageData = docCoverageData;
	}

	public List<Long> getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(List<Long> userGroupId) {
		this.userGroupId = userGroupId;
	}

}
