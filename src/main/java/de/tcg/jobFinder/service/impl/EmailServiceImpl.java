package de.tcg.jobFinder.service.impl;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl {
	 private final TemplateEngine templateEngine;

	    private final JavaMailSender javaMailSender;

	    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
	        this.templateEngine = templateEngine;
	        this.javaMailSender = javaMailSender;
	    }

	    public String sendMail(Object object, String templateName) throws MessagingException {
	        Context context = new Context();
	        context.setVariable("user", "test");

	        String process = templateEngine.process("emails/" + templateName, context);
	        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject("Welcome " + "test");
	        helper.setText(process, true);
	        helper.setTo("vantu7849@gmail.com");
	        javaMailSender.send(mimeMessage);
	        return "Sent";
	    }
}
