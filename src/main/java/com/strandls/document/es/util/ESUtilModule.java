package com.strandls.document.es.util;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * 
 * @author vishnu
 *
 */

public class ESUtilModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ESUtility.class).in(Scopes.SINGLETON);
		bind(ESUpdate.class).in(Scopes.SINGLETON);
		bind(RabbitMQProducer.class).in(Scopes.SINGLETON);
		bind(RabbitMQConsumer.class).in(Scopes.SINGLETON);
		bind(ESUpdateThread.class).in(Scopes.SINGLETON);
	}
}
