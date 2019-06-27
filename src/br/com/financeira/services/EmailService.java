package br.com.financeira.services;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;

import br.com.financeira.entities.Email;

@Stateless
public class EmailService {
	
	public void enviarEmail(Email email) throws EmailException {
		
		final String username = Email.MAIL_USER;
        final String password = Email.MAIL_SENDER_PASS;
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
//        /** Ativa Debug para sessão */
//        session.setDebug(true);

        try {
        	
        	String mailTo = email.getUsuario().getEmail();
        	if (!email.getUsuario().getFuncionarioList().isEmpty()) {
        		mailTo = email.getUsuario().getFuncionarioList().get(0).getEmail();
        	}
        	
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Email.MAIL_SENDER));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailTo)
            );
            message.setSubject(email.getAssunto());
            message.setText(email.getConteudo());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
//		try {
//			SimpleEmail simpleMail = new SimpleEmail(); 
//			simpleMail.setHostName(email.MAIL_HOST); 
//			simpleMail.addTo(email.getUsuario().getFuncionarioList().get(0).getEmail(), email.getUsuario().getFuncionarioList().get(0).getNome());
//			simpleMail.setFrom(email.MAIL_SENDER, "SAF - Sistema de Administração de Financeira"); 
//			simpleMail.setAuthentication(email.MAIL_USER, email.MAIL_SENDER_PASS);
//			simpleMail.setSubject(email.getAssunto()); 
//			simpleMail.setSmtpPort(Integer.parseInt(email.MAIl_PORT));
//			simpleMail.setMsg(email.getConteudo()); 
//			simpleMail.send(); 
//		} catch (EmailException e) {
//			e.printStackTrace();
//			throw e;
//		} 
	}
	
	public static void main(String[] args) {

        final String username = Email.MAIL_USER;
        final String password = Email.MAIL_SENDER_PASS;
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {
        	
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Email.MAIL_SENDER));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("monykeluiza@gmail.com")
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	

	

}
 