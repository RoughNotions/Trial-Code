package com.apc.PGRequestHandle.PGservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import com.apc.autotest.utils.FieldsMapInitilizer;
import com.apc.PGRequestHandle.PGutils.FieldsMapInitilizer;

public class RequestMapBuilder {

	public static void doInitilize(ArrayList<String> valuesList, int rownum) {

		Iterator fieldIterator = FieldsMapInitilizer.fieldsList.iterator();
		Iterator valIterator = valuesList.iterator();
		Map RequestMap = new HashMap<String, String>();
		/*
		 * while (fieldIterator.hasNext()) { String fval =
		 * fieldIterator.next().toString(); System.out.println("fval  "+fval);
		 * //if (fval.contains("<") && fval.contains(">")) { //
		 * RequestMap.put(fval, ""); //} else RequestMap.put(fval,
		 * valIterator.next().toString());
		 * 
		 * }
		 * 
		 * System.out.println("reqMAP"); Iterator iterator =
		 * RequestMap.keySet().iterator();
		 * 
		 * while (iterator.hasNext()) { String key = iterator.next().toString();
		 * String value = RequestMap.get(key).toString();
		 * 
		 * System.out.println(key + " " + value); }
		 */

		System.out.println(valuesList + "\n");
		System.out.println(FieldsMapInitilizer.fieldsList);

		StringBuilder reqbuilder = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>");

		while (fieldIterator.hasNext()) {
			String element = fieldIterator.next().toString();
			String val = valIterator.next().toString();
			System.out.println(element + "--" + val);
			if (element.contains("<")) {

				reqbuilder.append(element);
			} else {
				reqbuilder.append("<" + element + ">");
				if (!(val.equals("*"))) {
					reqbuilder.append(val);
				}
				reqbuilder.append("</" + element + ">");
			}

		}
		System.out.println("mainreq" + reqbuilder.toString());
		// RestClient.consumeRest(reqbuilder.toString(),rownum);
		ServiceFactory.getRestClient().consumeRest(reqbuilder.toString(),
				rownum);

	}

}