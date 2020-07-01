/**
 * 
 */
package com.strandls.document.controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DocumentController.class).in(Scopes.SINGLETON);
	}

}
