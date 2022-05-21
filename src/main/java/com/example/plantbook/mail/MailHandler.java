package com.example.plantbook.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailHandler {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send mail.
     * Sends and email to the given email address, with the given subject and body.
     *
     * @param email   the email address of the recipient
     * @param subject the subject of the email
     * @param message the message of the email
     */
    public void sendMail(String email, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("bodeatest@gmail.com");
        mail.setTo(email);
        mail.setText(message);
        mail.setSubject(subject);
        //System.out.println(1);
        mailSender.send(mail);
        //System.out.println(2);
        System.out.println("Mail sent");
    }
}
