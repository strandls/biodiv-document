/**
 * 
 */
package com.strandls.document.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;

/**
 * @author Abhishek Rudra
 *
 */

@Entity
@Table(name = "document")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429553368493728184L;
	private Long id;
	private Long version;
	private Boolean agreeTerms;
	private String attribution;
	private Long authorId;
	private String contributors;
	private Long coverageId;
	private Date createdOn;
	private String notes;
	private String doi;
	private Date lastRevised;
	private Long licenseId;
	private Long sourceHolderId;
	private String sourceHolderType;
	private String title;
	private String type;
	@JsonProperty(value = "uFileId")
	private Long uFileId;
	private Date fromDate;
	private Boolean geoPrivacy;
	private Long groupId;
	private Long habitatId;
	private Double latitude;
	private String locationAccuracy;
	private Double longitude;
	private String placeName;
	private String reverseGeoCodedName;
	private Date toDate;
	private Geometry topology;
	private Integer featureCount;
	private Integer flagCount;
	private Long languageId;
	private String locationScale;
	private Long dataSetId;
	private String externalId;
	private String externalUrl;
	private Date lastCrawled;
	private Date lastInterpreted;
	private String originalAuthor;
	private String viaCode;
	private String viaId;
	private Integer visitCount;
	private Integer rating;
	private Boolean isDeleted;
	private Long dataTableId;
	private String dateAccuracy;
	private String author;
	private String journal;
	private String bookTitle;
	private String year;
	private String month;
	private String volume;
	private String number;
	private String pages;
	private String publisher;
	private String school;
	private String edition;
	private String series;
	private String address;
	private String chapter;
	private String note;
	private String editor;
	private String organization;
	private String howPublished;
	private String institution;
	private String url;
	private String language;
	private String file;
	private String itemtype;
	private String isbn;
	private String extra;

	/**
	 * 
	 */
	public Document() {
		super();
	}

	/**
	 * @param id
	 * @param version
	 * @param agreeTerms
	 * @param attribution
	 * @param authorId
	 * @param contributors
	 * @param coverageId
	 * @param createdOn
	 * @param notes
	 * @param doi
	 * @param lastRevised
	 * @param licenseId
	 * @param sourceHolderId
	 * @param sourceHolderType
	 * @param title
	 * @param type
	 * @param uFileId
	 * @param fromDate
	 * @param geoPrivacy
	 * @param groupId
	 * @param habitatId
	 * @param latitude
	 * @param locationAccuracy
	 * @param longitude
	 * @param placeName
	 * @param reverseGeoCodedName
	 * @param toDate
	 * @param topology
	 * @param featureCount
	 * @param flagCount
	 * @param languageId
	 * @param locationScale
	 * @param dataSetId
	 * @param externalId
	 * @param externalUrl
	 * @param lastCrawled
	 * @param lastInterpreted
	 * @param originalAuthor
	 * @param viaCode
	 * @param viaId
	 * @param visitCount
	 * @param rating
	 * @param isDeleted
	 * @param dataTableId
	 * @param dateAccuracy
	 * @param author
	 * @param journal
	 * @param bookTitle
	 * @param year
	 * @param month
	 * @param volume
	 * @param number
	 * @param pages
	 * @param publisher
	 * @param school
	 * @param edition
	 * @param series
	 * @param address
	 * @param chapter
	 * @param note
	 * @param editor
	 * @param organization
	 * @param howPublished
	 * @param institution
	 * @param url
	 * @param language
	 * @param file
	 * @param itemtype
	 * @param isbn
	 * @param extra
	 */
	public Document(Long id, Long version, Boolean agreeTerms, String attribution, Long authorId, String contributors,
			Long coverageId, Date createdOn, String notes, String doi, Date lastRevised, Long licenseId,
			Long sourceHolderId, String sourceHolderType, String title, String type, Long uFileId, Date fromDate,
			Boolean geoPrivacy, Long groupId, Long habitatId, Double latitude, String locationAccuracy,
			Double longitude, String placeName, String reverseGeoCodedName, Date toDate, Geometry topology,
			Integer featureCount, Integer flagCount, Long languageId, String locationScale, Long dataSetId,
			String externalId, String externalUrl, Date lastCrawled, Date lastInterpreted, String originalAuthor,
			String viaCode, String viaId, Integer visitCount, Integer rating, Boolean isDeleted, Long dataTableId,
			String dateAccuracy, String author, String journal, String bookTitle, String year, String month,
			String volume, String number, String pages, String publisher, String school, String edition, String series,
			String address, String chapter, String note, String editor, String organization, String howPublished,
			String institution, String url, String language, String file, String itemtype, String isbn, String extra) {
		super();
		this.id = id;
		this.version = version;
		this.agreeTerms = agreeTerms;
		this.attribution = attribution;
		this.authorId = authorId;
		this.contributors = contributors;
		this.coverageId = coverageId;
		this.createdOn = createdOn;
		this.notes = notes;
		this.doi = doi;
		this.lastRevised = lastRevised;
		this.licenseId = licenseId;
		this.sourceHolderId = sourceHolderId;
		this.sourceHolderType = sourceHolderType;
		this.title = title;
		this.type = type;
		this.uFileId = uFileId;
		this.fromDate = fromDate;
		this.geoPrivacy = geoPrivacy;
		this.groupId = groupId;
		this.habitatId = habitatId;
		this.latitude = latitude;
		this.locationAccuracy = locationAccuracy;
		this.longitude = longitude;
		this.placeName = placeName;
		this.reverseGeoCodedName = reverseGeoCodedName;
		this.toDate = toDate;
		this.topology = topology;
		this.featureCount = featureCount;
		this.flagCount = flagCount;
		this.languageId = languageId;
		this.locationScale = locationScale;
		this.dataSetId = dataSetId;
		this.externalId = externalId;
		this.externalUrl = externalUrl;
		this.lastCrawled = lastCrawled;
		this.lastInterpreted = lastInterpreted;
		this.originalAuthor = originalAuthor;
		this.viaCode = viaCode;
		this.viaId = viaId;
		this.visitCount = visitCount;
		this.rating = rating;
		this.isDeleted = isDeleted;
		this.dataTableId = dataTableId;
		this.dateAccuracy = dateAccuracy;
		this.author = author;
		this.journal = journal;
		this.bookTitle = bookTitle;
		this.year = year;
		this.month = month;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		this.publisher = publisher;
		this.school = school;
		this.edition = edition;
		this.series = series;
		this.address = address;
		this.chapter = chapter;
		this.note = note;
		this.editor = editor;
		this.organization = organization;
		this.howPublished = howPublished;
		this.institution = institution;
		this.url = url;
		this.language = language;
		this.file = file;
		this.itemtype = itemtype;
		this.isbn = isbn;
		this.extra = extra;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "version")
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "agree_terms")
	public Boolean getAgreeTerms() {
		return agreeTerms;
	}

	public void setAgreeTerms(Boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}

	@Column(name = "attribution", columnDefinition = "TEXT")
	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	@Column(name = "author_id")
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Column(name = "contributors", columnDefinition = "TEXT")
	public String getContributors() {
		return contributors;
	}

	public void setContributors(String contributors) {
		this.contributors = contributors;
	}

	@Column(name = "coverage_id")
	public Long getCoverageId() {
		return coverageId;
	}

	public void setCoverageId(Long coverageId) {
		this.coverageId = coverageId;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "notes", columnDefinition = "TEXT")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "doi")
	public String getDoil() {
		return doi;
	}

	public void setDoil(String doi) {
		this.doi = doi;
	}

	@Column(name = "last_revised")
	public Date getLastRevised() {
		return lastRevised;
	}

	public void setLastRevised(Date lastRevised) {
		this.lastRevised = lastRevised;
	}

	@Column(name = "license_id")
	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	@Column(name = "source_holder_id")
	public Long getSourceHolderId() {
		return sourceHolderId;
	}

	public void setSourceHolderId(Long sourceHolderId) {
		this.sourceHolderId = sourceHolderId;
	}

	@Column(name = "source_holder_type")
	public String getSourceHolderType() {
		return sourceHolderType;
	}

	public void setSourceHolderType(String sourceHolderType) {
		this.sourceHolderType = sourceHolderType;
	}

	@Column(name = "title", columnDefinition = "TEXT")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type", columnDefinition = "TEXT")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "u_file_id")
	public Long getuFileId() {
		return uFileId;
	}

	public void setuFileId(Long uFileId) {
		this.uFileId = uFileId;
	}

	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "geo_privacy")
	public Boolean getGeoPrivacy() {
		return geoPrivacy;
	}

	public void setGeoPrivacy(Boolean geoPrivacy) {
		this.geoPrivacy = geoPrivacy;
	}

	@Column(name = "group_id")
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "habitat_id")
	public Long getHabitatId() {
		return habitatId;
	}

	public void setHabitatId(Long habitatId) {
		this.habitatId = habitatId;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "location_accuracy")
	public String getLocationAccuracy() {
		return locationAccuracy;
	}

	public void setLocationAccuracy(String locationAccuracy) {
		this.locationAccuracy = locationAccuracy;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "place_name")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Column(name = "reverse_geocoded_name")
	public String getReverseGeoCodedName() {
		return reverseGeoCodedName;
	}

	public void setReverseGeoCodedName(String reverseGeoCodedName) {
		this.reverseGeoCodedName = reverseGeoCodedName;
	}

	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "topology", columnDefinition = "Geometry", nullable = true)
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
	public Geometry getTopology() {
		return topology;
	}

	public void setTopology(Geometry topology) {
		this.topology = topology;
	}

	@Column(name = "feature_count")
	public Integer getFeatureCount() {
		return featureCount;
	}

	public void setFeatureCount(Integer featureCount) {
		this.featureCount = featureCount;
	}

	@Column(name = "flag_count")
	public Integer getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(Integer flagCount) {
		this.flagCount = flagCount;
	}

	@Column(name = "language_id")
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	@Column(name = "location_scale")
	public String getLocationScale() {
		return locationScale;
	}

	public void setLocationScale(String locationScale) {
		this.locationScale = locationScale;
	}

	@Column(name = "dataset_id")
	public Long getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(Long dataSetId) {
		this.dataSetId = dataSetId;
	}

	@Column(name = "external_id")
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@Column(name = "external_url")
	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	@Column(name = "last_crawled")
	public Date getLastCrawled() {
		return lastCrawled;
	}

	public void setLastCrawled(Date lastCrawled) {
		this.lastCrawled = lastCrawled;
	}

	@Column(name = "last_interpreted")
	public Date getLastInterpreted() {
		return lastInterpreted;
	}

	public void setLastInterpreted(Date lastInterpreted) {
		this.lastInterpreted = lastInterpreted;
	}

	@Column(name = "original_author")
	public String getOriginalAuthor() {
		return originalAuthor;
	}

	public void setOriginalAuthor(String originalAuthor) {
		this.originalAuthor = originalAuthor;
	}

	@Column(name = "via_code")
	public String getViaCode() {
		return viaCode;
	}

	public void setViaCode(String viaCode) {
		this.viaCode = viaCode;
	}

	@Column(name = "via_id")
	public String getViaId() {
		return viaId;
	}

	public void setViaId(String viaId) {
		this.viaId = viaId;
	}

	@Column(name = "visit_count")
	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "data_table_id")
	public Long getDataTableId() {
		return dataTableId;
	}

	public void setDataTableId(Long dataTableId) {
		this.dataTableId = dataTableId;
	}

	@Column(name = "date_accuracy")
	public String getDateAccuracy() {
		return dateAccuracy;
	}

	public void setDateAccuracy(String dateAccuracy) {
		this.dateAccuracy = dateAccuracy;
	}

	@Column(name = "author", columnDefinition = "TEXT")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "journal", columnDefinition = "TEXT")
	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Column(name = "book_title", columnDefinition = "TEXT")
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Column(name = "year")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "month")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "volume")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "pages")
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	@Column(name = "publisher", columnDefinition = "TEXT")
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "school")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "edition", columnDefinition = "TEXT")
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Column(name = "series", columnDefinition = "TEXT")
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "address", columnDefinition = "TEXT")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "chapter", columnDefinition = "TEXT")
	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	@Column(name = "note", columnDefinition = "TEXT")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "editor", columnDefinition = "TEXT")
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "organization", columnDefinition = "TEXT")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "how_published")
	public String getHowPublished() {
		return howPublished;
	}

	public void setHowPublished(String howPublished) {
		this.howPublished = howPublished;
	}

	@Column(name = "institution")
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Column(name = "extra", columnDefinition = "TEXT")
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	@Column(name = "url", columnDefinition = "TEXT")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "file", columnDefinition = "TEXT")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Column(name = "item_type")
	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	@Column(name = "isbn")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
