package com.strandls.document.util;

import java.util.HashMap;
import java.util.List;

import com.strandls.document.pojo.GNFinderResponseMap;
import com.strandls.document.pojo.GnFinderResponseNames;

public class BasicOperations {
	private static String concatValues(Object value1, Object value2) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[").append(value1).append(",").append(value2).append("]");
		return stringBuilder.toString();
	}
	
	
	public static final GNFinderResponseMap mergeCommonObjects(GNFinderResponseMap response) {
		HashMap<String, GnFinderResponseNames> scientificNameMap= new HashMap<String, GnFinderResponseNames>();
		List<GnFinderResponseNames> names = response.getNames();
		for (GnFinderResponseNames name: names) {
			String scientificNameKey = name.getName();
			String nameOffset = concatValues(name.getStart(),name.getEnd());
			if (scientificNameMap.containsKey(scientificNameKey)==true) {
				StringBuilder stringBuilder = new StringBuilder();
				GnFinderResponseNames existingName = scientificNameMap.get(scientificNameKey);
				String offSet = existingName.getOffSet();
				existingName.setOffSet(stringBuilder.append(offSet).append(",").append(nameOffset).toString());
				scientificNameMap.replace(scientificNameKey, existingName);
			}
			else {
				name.setOffSet(nameOffset);
				scientificNameMap.put(scientificNameKey, name);
			}	
		}
		response.setNames((List<GnFinderResponseNames>) scientificNameMap.values());
		return response;
	}
	
	public static final String parsePDF(String filePath) {
		
		return null;	
	}
	
	public static final String parseHTML(String html) {
		return null;
	}
	

}
