package com.sgb.server.resource;

import javax.mail.Address;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/email")
public class EmailResource {
	@Autowired 
	private JavaMailSender mailSender;
	
	@RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "brunoengecom@gmail.com" );
            helper.setSubject( "Teste Envio de e-mail" );
            helper.setText("<p>Usuario:Bruno</p>"
            			+ "<p>Livro: Não sei</p>"
            			+ "<p>patrimonio:12345</p>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }

}
