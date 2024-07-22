package co.edu.iudigital.senadoiud.services.impl;

import co.edu.iudigital.senadoiud.services.ifaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean sendMail(String message, String email, String subject) {
        boolean sent = false;
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("noreply@iudigital.edu.co");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.setSubject(subject);
            sender.send(mimeMessage);
            sent = true;
            log.info("Mensaje enviado exitosamente!");
        } catch (MessagingException e) {
            log.info("Error al enviar mensaje {}", e.getMessage());
        }
        return sent;
    }
}

