package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    //This is responsible to send mail
    public boolean sendEmail(String message, String subject, String to) {
        boolean f = false;

        String from = "kibitsujimuzan07@gmail.com";

        //variable for email
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties: "+properties);

        //setting important information to properties

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1: to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("kibitsujimuzan07@gmail.com", "kngx kkwv acwd qynu");
            }
        });
        session.setDebug(true);

        //step 2: compose the message
        MimeMessage message2 = new MimeMessage(session);

        try {
            //from email
            message2.setFrom(from);

            //adding recipient
            message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            message2.setSubject(subject);

            //adding text to message
            message2.setText(message);

            //step 3 : send the message using transport class
            Transport.send(message2);

            System.out.println("Email sent successfully!!");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
