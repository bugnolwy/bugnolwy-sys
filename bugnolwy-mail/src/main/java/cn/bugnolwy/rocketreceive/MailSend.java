package cn.bugnolwy.rocketreceive;

import cn.bugnolwy.vo.UserMail;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 接收mq信息发送mail
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Service
@RocketMQMessageListener(consumerGroup = "email-order", topic = "email-topic")
public class MailSend implements RocketMQListener<UserMail> {
	public static final Logger logger = LoggerFactory.getLogger(MailSend.class);
	
	@Autowired(required = false)
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public void onMessage(UserMail message) {
		try {
			sendEmail(message);
			logger.info("邮件发送成功!");
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.error("邮件发送失败:" + e.getMessage());
		}
	}
	
	public void sendEmail(UserMail userMail) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setSubject("标题");
		Context context = new Context();
		context.setVariable("username", userMail.getUsername());
		context.setVariable("password", userMail.getPassword());
		String html = templateEngine.process("mail", context);
		helper.setText(html, true);
		helper.setFrom("你的邮箱");
		helper.setSentDate(new Date());
		helper.setTo(userMail.getEmail());
		javaMailSender.send(message);
	}
}
