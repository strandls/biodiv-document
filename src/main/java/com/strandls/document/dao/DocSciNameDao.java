/**
 * 
 */
package com.strandls.document.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.google.inject.Inject;
import com.strandls.document.pojo.DocSciName;
import com.strandls.document.pojo.GNFinderResponseMap;
import com.strandls.document.pojo.GnFinderResponseNames;
import com.strandls.document.util.AbstractDAO;

/**
 * @author ashish
 *
 */
public class DocSciNameDao extends AbstractDAO<DocSciName, Long>{

	@Inject
	protected DocSciNameDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public DocSciName findById(Long id) {
		return null;
	}
	
	public void save(GNFinderResponseMap response, Long documentId) {
		DocSciName docSciName;
		List<GnFinderResponseNames>names = response.getNames();
		List<DocSciName> entityList = new ArrayList<DocSciName>();
		for(GnFinderResponseNames name : names) {
			docSciName = new DocSciName(2L, null, 0, documentId, name.getCardinality(), 
					name.getOffSet(), name.getName(), name.getTaxonId(), null, null);
			entityList.add(docSciName);
		}
		super.save(entityList);
	}

}
