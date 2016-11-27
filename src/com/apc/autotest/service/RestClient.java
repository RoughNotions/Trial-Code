package com.apc.autotest.service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import com.apc.autotest.service.*;
import com.apc.common.BaseTest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.net.ssl.X509TrustManager;

public class RestClient {

	public  void consumeRest(String xmlreq, int rownum) {

		try {
			TrustManager[] trustAllCerts = new TrustManager[]{
				    new X509TrustManager() {
				        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				            return null;
				        }
				        public void checkClientTrusted(
				            java.security.cert.X509Certificate[] certs, String authType) {
				        }
				        public void checkServerTrusted(
				            java.security.cert.X509Certificate[] certs, String authType) {
				        }
				    }
				};

				
				try {
				    SSLContext sc = SSLContext.getInstance("SSL");
				    sc.init(null, trustAllCerts, new java.security.SecureRandom());
				    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				} catch (Exception e) {
				}
			Client client = Client.create();

			WebResource webResource = client
					.resource(BaseTest.Conf.getProperty("API_URL"));

			// String input =
			// "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

			ClientResponse response = webResource.type("application/xml").post(
					ClientResponse.class, xmlreq);

			/*
			 * if (response.getStatus() != 201) { throw new
			 * RuntimeException("Failed : HTTP error code : " +
			 * response.getStatus()); }C
			 */

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
			// ResposeManager.manage(output, rownum);
			//ResponseParser.parse(output, rownum);
			ServiceFactory.getResponseParser().parse(output, rownum);
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
