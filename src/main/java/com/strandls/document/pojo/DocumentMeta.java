/**
 * 
 */
package com.strandls.document.pojo;

import java.util.Date;

import com.strandls.user.pojo.UserIbp;

/**
 * @author Abhishek Rudra
 *
 * 
 */
public class DocumentMeta implements Comparable<DocumentMeta> {

	private Long id;
	private String title;
	private String desc;
	private UserIbp author;
	private Date createdOnDate;
	private Integer displayOrder;

	/**
	 * 
	 */
	public DocumentMeta() {
		super();
	}

	/**
	 * @param id
	 * @param title
	 * @param desc
	 * @param author
	 * @param createdOnDate
	 * @param displayOrder
	 */
	public DocumentMeta(Long id, String title, String desc, UserIbp author, Date createdOnDate, Integer displayOrder) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.author = author;
		this.createdOnDate = createdOnDate;
		this.displayOrder = displayOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public UserIbp getAuthor() {
		return author;
	}

	public void setAuthor(UserIbp author) {
		this.author = author;
	}

	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public int compareTo(DocumentMeta o) {
		if (getCreatedOnDate() == null || o.getCreatedOnDate() == null)
			return 0;
		return getCreatedOnDate().compareTo(o.getCreatedOnDate());
	}

}
