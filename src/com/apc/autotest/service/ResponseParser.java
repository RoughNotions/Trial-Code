package com.apc.autotest.service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.apc.common.BaseTest;

public class ResponseParser {
	
	public static String responseCode;
	public static String responseMsg;
	public static String status;
	public static String FreeEmailDomain;
	public static String check;
	public static String check1;
	public static String IPAddressCountry;
	public static String IPCountryBIN;
	public static String ValidPhoneNo;
	public static String PrepaidCard;
	public static String ThirdPartyPurchase;
	public static String CountryCurrencyMatch;
	public static String HighRiskCountry;
	public static String BINCountryAddr;
	
	
	
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
				
			    status = resultElement
						.getElementsByTagName("Status").item(0)
						.getTextContent();
			    
			    
			    String Arr_SkipChecks[];
			    Arr_SkipChecks = BaseTest.getExecuteArray();   // this will get the array of Checks that needs to be executed.
			    
			    
			    for(int i=0;i<Arr_SkipChecks.length;i++)
			    {
			    	
			    	switch (Arr_SkipChecks[i])
			    	{
			    		case "check":
			    			System.out.println("check response executed");
					    	check = resultElement.getElementsByTagName("CheckResult").item(0).getTextContent();
					    	break;
					    	
					    case "check1":						    
					    	System.out.println("check1 response executed");	
					    	check1 = resultElement
			    				.getElementsByTagName("CheckResult").item(1)
				    				.getTextContent();
					    	break;
				    	
	
					    case "FreeEmailDomain":	
					    	System.out.println("FreeEmailDomain response executed");
					    	FreeEmailDomain =  resultElement
								.getElementsByTagName("FreeEmail").item(0)
								.getTextContent();
					    	break;
					    	
					    case "IPAddressCountry":	
					    	System.out.println("IPAddressCountry response executed");
					    		IPAddressCountry = resultElement
					    		.getElementsByTagName("IPCountryAddr").item(0)
					    		.getTextContent();
					    	break;
					    	
					    case "IPCountryBIN":	
					    	System.out.println("IPCountryBIN response executed");
					    	IPCountryBIN = resultElement
					    		.getElementsByTagName("IPCountryBIN").item(0)
					    		.getTextContent();
					    	break;
					    	
					    case "ValidPhoneNumber":
					    	System.out.println("ValidPhoneNumber response executed");
					    	ValidPhoneNo = resultElement
					    		.getElementsByTagName("ValidPhoneNumber").item(0)
					    		.getTextContent();
					    	break;			
					    	
					    case "PrepaidCard":
					    	System.out.println("PrepaidCard response executed");
					    	PrepaidCard = resultElement
					    		.getElementsByTagName("PrepaidCard").item(0)
					    		.getTextContent();
					    	break;			

					    case "ThirdPartyPurchase":
					    	System.out.println("ThirdPartyPurchase response executed");
					    	ThirdPartyPurchase = resultElement
					    		.getElementsByTagName("ThirdPartyPurchase").item(0)
					    		.getTextContent();
					    	break;		
					    
					    case "CountryCurrencyMatch":
					    	System.out.println("CountryCurrencyMatch response executed");
					    	CountryCurrencyMatch = resultElement
					    		.getElementsByTagName("CountryCurrencyMatch").item(0)
					    		.getTextContent();
					    	break;	
					    
					    case "HighRiskCountry":
					    	System.out.println("HighRiskCountry response executed");
					    	HighRiskCountry = resultElement
					    		.getElementsByTagName("HighRiskCountry").item(0)
					    		.getTextContent();
					    	break;				
					    	
					    case "BINCountryAddr":
					    	System.out.println("BINCountryAddr response executed");
					    	BINCountryAddr = resultElement
					    		.getElementsByTagName("BINCountryAddr").item(0)
					    		.getTextContent();
					    	break;									    	
					    	
					    	
					    	
			    	}
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