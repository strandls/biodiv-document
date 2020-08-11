/**
 * 
 */
package com.strandls.document.util;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.document.pojo.GNFinderResponseMap;
import com.strandls.document.pojo.GnFinderResponseNames;
import com.strandls.esmodule.ApiException;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.ExtendedTaxonDefinition;

/**
 * @author ashish
 *
 */

public class MicroServicesUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(MicroServicesUtils.class);
	
	public static GNFinderResponseMap fetchSpeciesDetails(GNFinderResponseMap response, EsServicesApi esServiceApi) {
		List<GnFinderResponseNames> names = response.getNames();
		List<GnFinderResponseNames> detailedNames = new ArrayList<GnFinderResponseNames>();
		for (GnFinderResponseNames name: names) {
			String canonicalName = name.getName();
			try {
				ExtendedTaxonDefinition taxonMapped = esServiceApi.matchPhrase("etd", "er", null, null, "canonical_form", canonicalName);
				Integer speciesId = taxonMapped.getSpeciesId();
				name.setSpeciesId(speciesId.toString());
				name.setTaxonId(new Long(taxonMapped.getId()));
				detailedNames.add(name);
			} catch (ApiException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		response.setNames(detailedNames);
		return response;
	}
	public static String getFileNameBasedOnTimeStamp() {
		Date date = new Date(); 
        Timestamp ts=new Timestamp(date.getTime());                      
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");  
        return formatter.format(ts).toString();
	}
	
	public static String pdfToText(String filePath) {
		File file = new File(filePath);
        PDDocument document;
        String text = null;
		try {
			document = PDDocument.load(file);
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			text  = pdfTextStripper.getText(document);
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return text;
	}
}
