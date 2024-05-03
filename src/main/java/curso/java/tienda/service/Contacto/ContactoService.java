package curso.java.tienda.service.Contacto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import curso.java.tienda.controller.base.BaseServlet;

public class ContactoService extends BaseServlet{

    public static void enviarEmail(String asunto, String mensaje) {

        final Properties properties = new Properties();
        String destinatario = "riders_shop@outlook.com";

        try (InputStream input = ContactoService.class.getClassLoader().getResourceAsStream("mail.properties")) {

            if (input == null) {
               throw new IOException("No se ha podido cargar el fichero de propiedades");
            }

            properties.load(input);

        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar el fichero de propiedades", ex);
        }

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.smtp.username"), properties.getProperty("mail.smtp.password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.username")));
            // message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);

        } catch (AddressException e) {
        	throw new RuntimeException("Error con la dirección del correo", e);
        	
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);        
		}
    }
}