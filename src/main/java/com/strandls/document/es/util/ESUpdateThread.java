/**
 * 
 */
package com.strandls.document.es.util;

/**
 * @author vishnu
 *
 */
public class ESUpdateThread implements Runnable {

	private ESUpdate esUpdate;
	private String documentId;

	/**
	 * 
	 */
	public ESUpdateThread() {
		super();
	}

	/**
	 * @param esUpdate
	 * @param observationId
	 */
	public ESUpdateThread(ESUpdate esUpdate, String documentId) {
		super();
		this.esUpdate = esUpdate;
		this.documentId = documentId;
	}

	@Override
	public void run() {
		esUpdate.updateESInstance(documentId);
	}

}
