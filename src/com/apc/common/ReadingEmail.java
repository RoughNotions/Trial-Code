package com.apc.common;



	/**
	 * @param args
	 */
	import java.util.*;
	import javax.mail.*;

	public class ReadingEmail {
	    public static void main(String[] args) {
	        Properties props = new Properties();
	        props.setProperty("mail.store.protocol", "pop3");
	   
	        try {
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore();
	            store.connect("pop3.yopmail.com", "testchetu13@yopmail.com", "tetratec2");
	            
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);
	            
	         ///   Message searc = inbox.search("Alphapayment");
	            Message msg = inbox.getMessage(inbox.getMessageCount());
	            Address[] in = msg.getFrom();
	            for (Address address : in) {
	                System.out.println("FROM:" + address.toString());
	            }
	            Multipart mp = (Multipart) msg.getContent();
	            BodyPart bp = mp.getBodyPart(0);
	            System.out.println("SENT DATE:" + msg.getSentDate());
	            System.out.println("SUBJECT:" + msg.getSubject());
	            System.out.println("CONTENT:" + bp.getContent());
	        } catch (Exception mex) {
	            mex.printStackTrace();
	        }
	    }
	}