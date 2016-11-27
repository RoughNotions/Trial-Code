/*
 * This code contains copyright information which is the proprietary property
 * of AlphaPayments. No part of this code may be reproduced, 
 * stored or transmitted in any form without the prior written permission.
 *
 * Copyright (C) Alphapayments All rights reserved.
 * ------------------------------------------------------------------------------
 * Version : 
 * Created on : Nov 27, 2013
 * Author : ansulg
 * Description : 
 * ------------------------------------------------------------------------------
 * Change History
 * ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package com.apc.autotest.handler;

import com.apc.autotest.service.ServiceFactory;


/**
 * @author ansulg
 * @version Nov 27, 2013
 */
public class AutoTestFacade {
	public static String EXCEL_PATH;
	public static String URL;
	public static void main(String[] args) {
		new AutoTestFacade().sendRequest("https://api.gpcloud.com/apc/rest/fms","Resourses\\Data\\API_Data.xlsx", 2);
		//new AutoTestFacade().sendRequest("d:\\one.xlsx", 2);
		//new AutoTestFacade().sendRequest("d:\\one.xlsx", 3);
		//new AutoTestFacade().sendRequest("d:\\one.xlsx", 4);
		//new AutoTestFacade().sendRequest("d:\\one.xlsx", 5);
/*System.out.println("Check result is #####"+ResponseParser.Check);
System.out.println("Check result is #####1"+ResponseParser.responseCode);
System.out.println("Check result is #####2"+ResponseParser.status);
System.out.println("Check result is #####2"+ResponseParser.responseMsg);*/
	}

	
	public void sendRequest(String url, String path, int rownum) {
		rownum = rownum-1;
		EXCEL_PATH = path;
		URL = url;
		ServiceFactory.getFieldMapInitilizer().doIntitilize(path, rownum);
		// FieldsMapInitilizer.doIntitilize(path, rownum);
		ServiceFactory.getValuesMapInitilizer().doInitilize(rownum);
		// ValuesMapInitilizer.doInitilize(rownum);
		// ValuesMapInitilizer.doInitilize(1);
		// MapToXML.toXML(xmlmap,"TransactionRequest");
	}

}
