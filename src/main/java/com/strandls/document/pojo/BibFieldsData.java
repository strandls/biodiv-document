/**
 * 
 */
package com.strandls.document.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Abhishek Rudra
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BibFieldsData {

	private String author;
	private String journal;
	private String booktitle;
	private String title;
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
	private String type;
	private String editor;
	private String organization;
	private String howpublished;
	private String institution;
	@JsonProperty(value = "abstract")
	private String description;
	private String doi;
	private String url;
	private String language;
	private String file;
	@JsonProperty(value = "item type")
	private String itemtype;
	private Long itemTypeId;
	private String isbn;
	private String extra;

	/**
	 * 
	 */
	public BibFieldsData() {
		super();
	}

	/**
	 * @param author
	 * @param journal
	 * @param booktitle
	 * @param title
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
	 * @param type
	 * @param editor
	 * @param organization
	 * @param howpublished
	 * @param institution
	 * @param description
	 * @param doi
	 * @param url
	 * @param language
	 * @param file
	 * @param itemtype
	 * @param itemTypeId
	 * @param isbn
	 * @param extra
	 */
	public BibFieldsData(String author, String journal, String booktitle, String title, String year, String month,
			String volume, String number, String pages, String publisher, String school, String edition, String series,
			String address, String chapter, String note, String type, String editor, String organization,
			String howpublished, String institution, String description, String doi, String url, String language,
			String file, String itemtype, Long itemTypeId, String isbn, String extra) {
		super();
		this.author = author;
		this.journal = journal;
		this.booktitle = booktitle;
		this.title = title;
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
		this.type = type;
		this.editor = editor;
		this.organization = organization;
		this.howpublished = howpublished;
		this.institution = institution;
		this.description = description;
		this.doi = doi;
		this.url = url;
		this.language = language;
		this.file = file;
		this.itemtype = itemtype;
		this.itemTypeId = itemTypeId;
		this.isbn = isbn;
		this.extra = extra;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getHowpublished() {
		return howpublished;
	}

	public void setHowpublished(String howpublished) {
		this.howpublished = howpublished;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
