package cn.bugnolwy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
public class BugnoLwyApplicationTests {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	
	@Test
	public void contextLoads1() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject("主题");
		simpleMailMessage.setText("bugnolwylwy");
		simpleMailMessage.setFrom("你的邮箱");
		simpleMailMessage.setSentDate(new Date());
		simpleMailMessage.setTo("你想发送的邮箱");
		javaMailSender.send(simpleMailMessage);
	}
	
	@Test
	public void contextLoads2() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("主题");
		helper.setText("text",true);
		helper.setFrom("你的邮箱");
		helper.setSentDate(new Date());
		helper.setTo("你想发送的邮箱");
		File file = new File("建议内容根路径,linux建议绝对路径");
		helper.addAttachment("bu.jpeg",file);
		System.out.println(file);
		javaMailSender.send(message);
	}
	
	@Test
	public void contextLoads3() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("subject");
		Context context = new Context();
		context.setVariable("username","username");
		context.setVariable("password","password");
		String mail = templateEngine.process("mail", context);
		helper.setText(mail,true);
		helper.setFrom("你的邮箱");
		helper.setSentDate(new Date());
		helper.setTo("你想发送的邮箱");
		javaMailSender.send(message);
	}
}
