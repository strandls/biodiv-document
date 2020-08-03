/**
 * 
 */
package com.strandls.document.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentDaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DocumentDao.class).in(Scopes.SINGLETON);
		bind(DocumentHabitatDao.class).in(Scopes.SINGLETON);
		bind(DocumentSpeciesGroupDao.class).in(Scopes.SINGLETON);
		bind(DocumentCoverageDao.class).in(Scopes.SINGLETON);
		bind(BibTexItemTypeDao.class).in(Scopes.SINGLETON);
		bind(BibTexFieldTypeDao.class).in(Scopes.SINGLETON);
		bind(BibTexItemFieldMappingDao.class).in(Scopes.SINGLETON);
	}

}
