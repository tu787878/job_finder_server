package de.tcg.jobFinder.service.impl;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import de.tcg.jobFinder.template.email.CommonEmailInformation;

@Service
public class EmailServiceImpl {
	 private final TemplateEngine templateEngine;

	    private final JavaMailSender javaMailSender;

	    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
	        this.templateEngine = templateEngine;
	        this.javaMailSender = javaMailSender;
	    }

	    public String sendMail(CommonEmailInformation information) throws MessagingException {
	        Context context = new Context();
	        context.setVariable("data", information);

	        String process = templateEngine.process("emails/" + information.getTemplateEmail(), context);
	        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
	        helper.setSubject(information.getSubtitle());
	        helper.setText(process, true);
	        helper.setTo(information.getEmailTo());
	        javaMailSender.send(mimeMessage);
	        return "Sent";
	    }
}
