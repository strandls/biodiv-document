package com.strandls.document.es.util;

public enum DocumentIndex {

	index("document"),
	type("document_records"), 
	sGroup("document.speciesGroupIds"),
	habitatList("document,habitatIds"),
	user("document.document.author_id"),
	featured("document.featured.id"),
	flagCount("docuemnt.document.flag_count"),
	flag("document.document.flag.id"),
	userGroupList("document.userGroupIbp.id"),
	createdOn("document.document.created_on"),
	lastRevised("document.document.last_revised"),
	tags("document.tags.name.keyword"),
	habitatIds("document.habitatIds.raw")
	;
	
	
	private String field;

	private DocumentIndex(String field) {
		this.field = field;
	}

	public String getValue() {
		return field;
	}
}
