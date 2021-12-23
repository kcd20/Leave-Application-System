package nus.iss.ca.leave_application.services;

import javax.transaction.Transactional;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import nus.iss.ca.leave_application.model.User;
import nus.iss.ca.leave_application.repositories.UserRepository;

@Component
@Service
public class EmailService{

//	@Autowired
//	UserRepository urepo;

	@Autowired
	private JavaMailSender emailSender; //from Java mail

//	@Transactional
//	public boolean sendEmail(User from, User to) {
//		// TODO Auto-generated method stub
//		//Insert email code here
//		return true;
//	}

	//sendVerificationEmail(User);

//	public boolean sendConfirmationEmail(User user, String siteURL) {
//		String subject = "Subject";
//		String senderName = "Admin";
//		String mailContent = "<p> Dear" + user.getName() + ",</p>";
//		mailContent += "<p>You have received a leave application</p>";
//		mailContent += "<h3><a = \"href=" + siteURL + "\">Back to Main</a></h3>";
//		mailContent += "<p>Thank you</p>";
//		
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		
//		helper.setFrom("joshualeowzhihao@gmail.com", senderName);
//		helper.setTo(user.getEmailAddress());
//		helper.setSubject(subject);
//		helper.setText(mailContent, true);
//		
//		mailSender.send(message);
//	}

	public String sendAppEmail(String toEmail, String subject, String text) {
			try {
				MimeMessage themessage = emailSender.createMimeMessage();

				MimeMessageHelper messageHelper = new MimeMessageHelper(themessage, true);

				messageHelper.setFrom("joshualeowtest@gmail.com");
				messageHelper.setTo(toEmail);
				messageHelper.setText(text, true);
				messageHelper.setSubject(subject);

				//File file = new File("path to attachement");

				//messageHelper.addAttachment(file.getName(), file);

				emailSender.send(themessage);

				return "Mail was sent successfully";

			} catch (Exception e) {
				return "Mail Failed";
			}

		}
}

//	public ApplicationEmail(JavaMailSender emailSender) {
//		this.emailSender = emailSender;
//	}

