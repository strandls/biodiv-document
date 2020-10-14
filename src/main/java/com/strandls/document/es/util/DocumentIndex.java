package com.strandls.document.es.util;

public enum DocumentIndex {

	index("document"),
	type("document_records"), 
	sGroup("speciesGroupIds"),
	user("document.author_id"),
	featured("featured.id"),
	flagCount("document.flag_count"),
	flag("flag.id"),
	userGroupList("userGroupIbp.id"),
	createdOn("document.created_on"),
	lastRevised("document.last_revised"),
	tags("tags.name.keyword"),
	habitatIds("habitatIds")
	;
	
	
	private String field;

	private DocumentIndex(String field) {
		this.field = field;
	}

	public String getValue() {
		return field;
	}
}
