package com.edu.surfing.model.util;

import java.io.UnsupportedEncodingException;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.EmailException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailManager {
	private final JavaMailSender javaMailSender;

	// 메일보내기
	public void send(Member member) throws EmailException {

		// 이메일 정보를 담을 메시지 객체를 생성
		MimeMessage msg = javaMailSender.createMimeMessage();

		try {
			// 받을 사람 정보 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(member.getEmail()));

			// 보내는 사람 정보 설정
			msg.addFrom(new InternetAddress[] { new InternetAddress("eduzino1187@gmail.com", "webmaster") });

			// 메일제목
			msg.setSubject("쇼핑몰 회원가입 완료");

			// 메일내용
			msg.setText("회원가입을 축하드립니다", "utf-8");

			// 메일전송
			javaMailSender.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailException("받는사람 정보 설정 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("받는사람 정보 설정 실패", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new EmailException("보내는 사람 정보 설정 실패", e);
		}

	}
}