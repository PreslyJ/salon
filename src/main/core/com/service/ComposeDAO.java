
package com.service;

import java.util.Properties;
import javax.mail.*;  
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ComposeDAO {

	public static String send(String reciever, String subject,String message) {
		if(reciever==null||message==null){
			return "Please fill the requried fields";
		}else{
			  String to = reciever;//change accordingly
			  String from = "preslychedhena@gmail.com";//change accordingly
		      final String username = "preslychedhena@gmail.com";//change accordingly
		      final String password = "trinity0000";//change accordingly

		      // Assuming you are sending email through relay.jangosmtp.net
		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });
		      
		      try {
		          // Create a default MimeMessage object.
		          Message msg = new MimeMessage(session);

		          // Set From: header field of the header.
		          msg.setFrom(new InternetAddress(from));

		          // Set To: header field of the header.
		          msg.setRecipients(Message.RecipientType.TO,
		          InternetAddress.parse(to));

		          // Set Subject: header field
		          msg.setSubject(subject);

		          // Now set the actual message
		          msg.setText(message);

		          // Send message		
		          Transport.send(msg);

		          System.out.println("Sent message successfully....");

		      
			/*
			  // Recipient's email ID needs to be mentioned.
		      String to = receiver;
		 
		      // Sender's email ID needs to be mentioned
		      String from = sender;
		 
		      // Assuming you are sending email from localhost
		      String host = "localhost";
		 
		      // Get system properties
		      Properties properties = System.getProperties();
		 
		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);
		 
		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);
			
		      try{
		          // Create a default MimeMessage object.
		          MimeMessage msg = new MimeMessage(session);
		          // Set From: header field of the header.
		          msg.setFrom(new InternetAddress(from));
		          // Set To: header field of the header.
		          msg.addRecipient(Message.RecipientType.TO,
		                                   new InternetAddress(to));
		          // Set Subject: header field
		          msg.setSubject(subject);
		          // Now set the actual message
		          msg.setText(message);
		          // Send message
		          Transport.send(msg);
		          
		       }catch (MessagingException mex) {
		          mex.printStackTrace();
		       }*/
		 			return "email sent  succesfully";
		       } catch (MessagingException e) {
		             throw new RuntimeException(e);

		       }
		}
	}

}
