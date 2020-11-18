package com.strandls.document.es.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.esmodule.pojo.MapAndBoolQuery;
import com.strandls.esmodule.pojo.MapAndMatchPhraseQuery;
import com.strandls.esmodule.pojo.MapAndRangeQuery;
import com.strandls.esmodule.pojo.MapExistQuery;
import com.strandls.esmodule.pojo.MapOrBoolQuery;
import com.strandls.esmodule.pojo.MapOrMatchPhraseQuery;
import com.strandls.esmodule.pojo.MapOrRangeQuery;
//import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.MapSearchParams;
import com.strandls.esmodule.pojo.MapSearchQuery;

/**
 * 
 * @author vishnu
 *
 */

public class ESUtility {
	private final Logger logger = LoggerFactory.getLogger(ESUtility.class);

//	@Inject
//	private EsServicesApi esService;

	private List<Object> cSTSOT(String str) {
		if (str == null || str == "" || str.isEmpty())
			return new ArrayList<Object>();

		String[] y = str.split(",");
		Set<Object> strSet1 = Arrays.stream(y).collect(Collectors.toSet());
		List<Object> strList = new ArrayList<Object>();
		strList.addAll(strSet1);
		return strList;

	}

	private MapAndBoolQuery assignBoolAndQuery(String key, List<Object> values) {
		MapAndBoolQuery andBool = new MapAndBoolQuery();
		andBool.setKey(key);
		andBool.setValues(values);
		return andBool;

	}

	@SuppressWarnings("unused")
	private MapOrBoolQuery assOrBoolQuery(String key, List<Object> values) {
		MapOrBoolQuery orBool = new MapOrBoolQuery();
		orBool.setKey(key);
		orBool.setValues(values);
		return orBool;
	}

	@SuppressWarnings("unused")
	private MapExistQuery assignExistsQuery(String key, Boolean values, String path) {
		MapExistQuery existQuery = new MapExistQuery();
		existQuery.setKey(key);
		existQuery.setExists(values);
		existQuery.setPath(path);
		return existQuery;

	}

	@SuppressWarnings("unused")
	private MapAndMatchPhraseQuery assignAndMatchPhrase(String key, String value) {
		MapAndMatchPhraseQuery andMatchPhrase = new MapAndMatchPhraseQuery();
		andMatchPhrase.setKey(key);
		andMatchPhrase.setValue(value);
		return andMatchPhrase;
	}

	private MapOrMatchPhraseQuery assignOrMatchPhrase(String key, String value) {
		MapOrMatchPhraseQuery orMatchPhrase = new MapOrMatchPhraseQuery();
		orMatchPhrase.setKey(key);
		orMatchPhrase.setValue(value);
		return orMatchPhrase;
	}

	private MapAndRangeQuery assignAndRange(String key, Object start, Object end, String path) {
		MapAndRangeQuery andRange = new MapAndRangeQuery();
		andRange.setKey(key);
		andRange.setStart(start);
		andRange.setEnd(end);
		andRange.setPath(path);
		return andRange;
	}

	@SuppressWarnings("unused")
	private MapOrRangeQuery assignOrRange(String key, Object start, Object end) {
		MapOrRangeQuery orRange = new MapOrRangeQuery();
		orRange.setKey(key);
		orRange.setStart(start);
		orRange.setEnd(end);
		return orRange;
	}

	public MapSearchQuery getMapSearchQuery(String sGroup, String habitatIds, String tags, String user, String flags,
			String createdOnMaxDate, String createdOnMinDate, String featured, String userGroupList, String isFlagged,
			String revisedOnMinDate, String revisedOnMaxDate, MapSearchParams mapSearchParams) {

		List<MapAndBoolQuery> boolAndLists = new ArrayList<MapAndBoolQuery>();
		List<MapOrBoolQuery> boolOrLists = new ArrayList<MapOrBoolQuery>();
		List<MapOrRangeQuery> rangeOrLists = new ArrayList<MapOrRangeQuery>();
		List<MapAndRangeQuery> rangeAndLists = new ArrayList<MapAndRangeQuery>();
		List<MapExistQuery> andMapExistQueries = new ArrayList<MapExistQuery>();
		List<MapAndMatchPhraseQuery> andMatchPhraseQueries = new ArrayList<MapAndMatchPhraseQuery>();
		List<MapOrMatchPhraseQuery> orMatchPhraseQueriesnew = new ArrayList<MapOrMatchPhraseQuery>();

		try {

//			tags
			List<Object> tagsList = cSTSOT(tags);
			if (!tagsList.isEmpty()) {
				List<Object> lowerCaseList = new ArrayList<Object>();
				for (Object o : tagsList) {
					String result = o.toString().toLowerCase();
					lowerCaseList.add(result);
				}
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.tags.getValue(), lowerCaseList));
			}

//			userGroupList
			List<Object> ugList = cSTSOT(userGroupList);
			if (!ugList.isEmpty()) {
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.userGroupList.getValue(), ugList));
			}

//			speciesGroupList
			String[] sgList = sGroup.split(",");
			if (sgList.length >= 1) {
				for (String o : sgList) {
					orMatchPhraseQueriesnew.add(assignOrMatchPhrase(DocumentIndex.sGroup.getValue(), o));
				}

			}
//			habitatId List
			String[] habitatList = habitatIds.split(",");
			if (habitatList.length >= 1) {
				for (String o : habitatList) {
					orMatchPhraseQueriesnew.add(assignOrMatchPhrase(DocumentIndex.habitatIds.getValue(), o));
				}

			}
//			tags
			List<Object> tagList = cSTSOT(tags);
			if (!tagList.isEmpty()) {
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.tags.getValue(), tagList));
			}

//			featured
			List<Object> featuredList = cSTSOT(featured);
			if (!featuredList.isEmpty()) {
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.featured.getValue(), featuredList));
			}

//			flags
			List<Object> flagList = cSTSOT(flags);
			if (!flagList.isEmpty()) {
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.flag.getValue(), flagList));
			}
//			user
			List<Object> authorId = cSTSOT(user);
			if (!authorId.isEmpty()) {
				boolAndLists.add(assignBoolAndQuery(DocumentIndex.user.getValue(), authorId));
			}

//			Data Quality:- Flagged
			List<Object> flagged = cSTSOT(isFlagged);
			if (!flagged.isEmpty()) {

				if (flagged.size() < 2) {
					String first = (String) flagged.toArray()[0];
					if (first.equalsIgnoreCase("1")) {
						rangeAndLists
								.add(assignAndRange(DocumentIndex.flagCount.getValue(), first, Long.MAX_VALUE, null));
					}
					if (first.equalsIgnoreCase("0")) {
						rangeAndLists.add(assignAndRange(DocumentIndex.flagCount.getValue(), first, first, null));
					}

				}
			}

//			Created on
			String createdOnMaxDateValue = null;
			String createdOnMinDateValue = null;
			Date date = new Date();
			SimpleDateFormat out = new SimpleDateFormat("YYYY-MM-dd");
			try {
				if (createdOnMinDate != null) {
					createdOnMinDateValue = createdOnMinDate;
				}
				if (createdOnMaxDate != null) {
					createdOnMaxDateValue = createdOnMaxDate;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (createdOnMinDateValue != null && createdOnMaxDateValue != null) {

				rangeAndLists.add(assignAndRange(DocumentIndex.createdOn.getValue(), createdOnMinDateValue,
						createdOnMaxDateValue, null));
			}
			if (createdOnMinDateValue != null && createdOnMaxDateValue == null) {
				rangeAndLists.add(assignAndRange(DocumentIndex.createdOn.getValue(), createdOnMinDateValue,
						out.format(date), null));
			}
			if (createdOnMinDateValue == null && createdOnMaxDateValue != null) {
				rangeAndLists.add(assignAndRange(DocumentIndex.createdOn.getValue(), out.format(date),
						createdOnMaxDateValue, null));
			}

//			revised on

			String revisedOnMaxDateValue = null;
			String revisedOnMinDateValue = null;

			try {
				if (revisedOnMinDate != null) {
					revisedOnMinDateValue = revisedOnMinDate;
				}
				if (revisedOnMaxDate != null) {
					revisedOnMaxDateValue = revisedOnMaxDate;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (revisedOnMinDateValue != null && revisedOnMaxDateValue != null) {

				rangeAndLists.add(assignAndRange(DocumentIndex.lastRevised.getValue(), revisedOnMaxDateValue,
						revisedOnMinDateValue, null));
			}
			if (revisedOnMinDateValue != null && revisedOnMaxDateValue == null) {
				rangeAndLists.add(assignAndRange(DocumentIndex.lastRevised.getValue(), out.format(date),
						revisedOnMinDateValue, null));
			}
			if (revisedOnMinDateValue == null && revisedOnMaxDateValue != null) {
				rangeAndLists.add(assignAndRange(DocumentIndex.lastRevised.getValue(), revisedOnMaxDateValue,
						out.format(date), null));
			}

			/**
			 * combine all the queries
			 * 
			 */
			MapSearchQuery mapSearchQuery = new MapSearchQuery();

			mapSearchQuery.setAndBoolQueries(boolAndLists);
			mapSearchQuery.setOrBoolQueries(boolOrLists);
			mapSearchQuery.setAndRangeQueries(rangeAndLists);
			mapSearchQuery.setOrRangeQueries(rangeOrLists);
			mapSearchQuery.setAndExistQueries(andMapExistQueries);
			mapSearchQuery.setAndMatchPhraseQueries(andMatchPhraseQueries);
			mapSearchQuery.setOrMatchPhraseQueries(orMatchPhraseQueriesnew);
			mapSearchQuery.setSearchParams(mapSearchParams);

			return mapSearchQuery;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}
}
