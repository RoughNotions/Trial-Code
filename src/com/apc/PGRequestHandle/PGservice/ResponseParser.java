package com.apc.PGRequestHandle.PGservice;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//import com.apc.common.BaseTest;
import com.apc.PGportal.BaseTestPG;


public class ResponseParser {
	
	public static String responseCode;
	public static String responseMsg;
	public static String status;
	public static String PaymentID;
	
	public static boolean IsNeedToCapturePaymentID =false;
	
	
	
	public void parse(String response, int rownum) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(new InputSource(new StringReader(
					response)));
			doc.getDocumentElement().normalize();

			// response code
			NodeList resultList = doc.getElementsByTagName("TransactionResponse");
			Node resultNode = resultList.item(0);
			
			
			/*NodeList resultList_f = doc.getElementsByTagName("FraudChecks");
			Node resultNode_A = resultList_f.item(0);
			
			
			Element resultElement_f = (Element) resultNode_A;*/
			
			Element resultElement = (Element) resultNode;
			//if (resultNode.getNodeType() == Node.ELEMENT_NODE) {

				

				 responseCode = resultElement
						.getElementsByTagName("ResponseCode").item(0)
						.getTextContent();
			    responseMsg = resultElement
						.getElementsByTagName("ResponseMessage").item(0)
						.getTextContent();
			    
			    
			    if(IsNeedToCapturePaymentID)
			    {
			    	PaymentID = resultElement
			    			.getElementsByTagName("PaymentID").item(0)
			    			.getTextContent();
			    }
			    
			  
				ServiceFactory.getResponseManager().manage(response, rownum,
						responseCode, responseMsg);
			//}

			/*
			 * String responseCode = resCodeNode.getNodeValue();
			 * 
			 * //response message
			 * 
			 * NodeList resMsglist =
			 * doc.getElementsByTagName("ResponseMessage"); Node resMsgNode =
			 * resMsglist.item(0);
			 * 
			 * String responseMsg = resMsgNode.getNodeValue();
			 * 
			 * System.out.println("responsecode "+responseCode);
			 * System.out.println("responsemsg "+responseMsg);
			 * 
			 * //ResposeManager.manage(response,
			 * rownum,responseCode,responseMsg);
			 * System.out.println("+++++++++++++++++++"+response);
			 * ResposeManager.manage(response, rownum);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}