package com.apc.autotest.service;

import com.apc.autotest.utils.FieldsMapInitilizer;

public class ServiceFactory {

	static FieldsMapInitilizer fieldsMapInitilizer;

	static {

		fieldsMapInitilizer = new FieldsMapInitilizer();
	}

	public static FieldsMapInitilizer getFieldMapInitilizer() {
		if (fieldsMapInitilizer == null) {
			fieldsMapInitilizer = new FieldsMapInitilizer();
		}
		return fieldsMapInitilizer;
	}

	public static ValuesMapInitilizer getValuesMapInitilizer() {

		return new ValuesMapInitilizer();

	}

	public static RequestMapBuilder getRequestMapBuilder() {

		return new RequestMapBuilder();

	}

	public static RestClient getRestClient() {

		return new RestClient();

	}

	public static ResponseParser getResponseParser() {

		return new ResponseParser();

	}

	public static ResponseManager getResponseManager() {

		return new ResponseManager();

	}

}
