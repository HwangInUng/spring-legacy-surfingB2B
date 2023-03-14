package com.edu.surfing.model.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailSendService {
	private final JavaMailSenderImpl mailSender;
	private int authCode; // 인증코드로 사용될 난수 담을 변수

	// 인증코드생성 메소드
	public int makeRandomNumber() {
		Random r = new Random();
		return r.nextInt(888888) + 111111;
	}

	// 이메일 양식 생성 메소드
	public int sendEmail(String email) {
		authCode = makeRandomNumber();

		String from = "ung6860@gmail.com";
		String title = "회원 가입 인증 이메일 입니다.";
		StringBuilder content = new StringBuilder();
		content.append("홈페이지를 방문해주셔서 감사합니다.");
		content.append("<br><br>");
		content.append("인증 번호는 " + authCode + "입니다.");
		content.append("<br>" + "해당 인증번호를 인증번호 확인란에 기입하여 주세요.");
		
		setEmailData(from, email, title, content.toString());
		
		return authCode;
	}
	
	//이메일 전송 메소드
	public void setEmailData(String from, String email, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
