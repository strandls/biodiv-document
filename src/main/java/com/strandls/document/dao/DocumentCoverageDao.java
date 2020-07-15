/**
 * 
 */
package com.strandls.document.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.document.pojo.DocumentCoverage;
import com.strandls.document.util.AbstractDAO;

/**
 * @author Abhishek Rudra
 *
 */
public class DocumentCoverageDao extends AbstractDAO<DocumentCoverage, Long> {

	private final Logger logger = LoggerFactory.getLogger(DocumentCoverageDao.class);

	/**
	 * @param sessionFactory
	 */
	@Inject
	protected DocumentCoverageDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public DocumentCoverage findById(Long id) {
		DocumentCoverage result = null;
		Session session = sessionFactory.openSession();
		try {
			result = session.get(DocumentCoverage.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}

}
