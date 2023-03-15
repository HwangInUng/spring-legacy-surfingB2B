package com.edu.surfing.model.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final JavaMailSenderImpl mailSender;
	private int authCode; // 인증코드로 사용될 난수 담을 변수

	// 인증코드생성 메소드
	public int makeRandomNumber() {
		Random r = new Random();
		return r.nextInt(888888) + 111111;
	}
	
	/*
	 * 메일 인증의 경우 서버 클러스터링 환경에서 동작 시 저장한 서버와 다른 서버의
	 * 사용자 요청이 전달될 가능성이 있어, 인증번호가 유효하지 않을 수 있음
	 * 이런 경우를 대신해 Redis나 Memcached등의 분산 캐시를 사용하는 것을 고려해야함.
	 */
	
	
	// 인증이메일 메소드
	public int sendEmail(String email) {
		//인증 코드 생성
		authCode = makeRandomNumber();

		//발신자 정보
		String from = "ung6860@gmail.com";
		String title = "회원 가입 인증 이메일 입니다.";
		
		//수신받을 내용
		StringBuilder content = new StringBuilder();
		
		content.append("홈페이지를 방문해주셔서 감사합니다.");
		content.append("<br><br>");
		content.append("인증 번호는 " + authCode + " 입니다."); //인증번호 포함
		content.append("<br>해당 인증번호를 인증번호 확인란에 기입하여 주세요.");
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(from);
			helper.setTo(email);
			helper.setSubject(title);
			helper.setText(content.toString(), true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return authCode;
	}
	
	// 인증문자 메소드
	public int sendMessage(String phoneNo) {
		authCode = makeRandomNumber();
		
		String apiKey = "NCSVK1KULQKSXRNG";
		String apiSecretKey = "IZMRNSVTLENJKWGDP29GDMH4N3UBQAD2";
		String domain = "https://api.coolsms.co.kr";
		String from = "01091716860";
		String to = phoneNo;
		
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, domain);
		
		Message message = new Message();
		//발신 및 수신번호는 '-'을 제외하고 입력
		message.setFrom(from);
		message.setTo(to);
		message.setText("인증번호는 " + authCode + " 확인란에 입력해주세요.");
		
		SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
		
		return authCode;
	}
}
