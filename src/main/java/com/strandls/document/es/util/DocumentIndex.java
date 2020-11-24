package com.strandls.document.es.util;

public enum DocumentIndex {

	index("document"),
	type("document_records"), 
	sGroup("speciesGroupIds"),
	user("document.authorId"),
	featured("featured.id"),
	flagCount("document.flagCount"),
	flag("flag.id"),
	userGroupList("userGroupIbp.id"),
	createdOn("document.createdOn"),
	lastRevised("document.lastRevised"),
	state("documentCoverages.placename.keyword"),
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
