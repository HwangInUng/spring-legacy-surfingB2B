package com.edu.surfing.model.reservation;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.edu.surfing.domain.reservation.Payment;
import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.domain.reservation.TossPayment;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor
public class TossClient {
	@Value("${toss.url}")
	private String toss_url;
	@Value("${toss.secret}")
	private String toss_secret;
	
	public Payment getConfirmPayment(PaymentParams paymentParams) {
		String authorization = Base64.getEncoder().encodeToString(toss_secret.getBytes());
		log.debug("------ API 전송 요청 -------" + authorization);
		
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + authorization);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		HttpEntity<PaymentParams> paymentRequest = new HttpEntity<>(paymentParams, headers);
		
		log.debug("요청Url:: " + toss_url);
		TossPayment tossPayment = rt.postForObject(toss_url, paymentRequest, TossPayment.class);
		log.debug("응답받은 tossPayment:: " + tossPayment.getMethod());
		log.debug("응답받은 tossPayment:: " + tossPayment.getOrderName());
		
		log.debug("------ API 응답 반환 -------");
		return getPaymentInfo(tossPayment, paymentParams);
	}
	
	//결제 승인결과를 매핑한 Payment객체 반환
	public Payment getPaymentInfo(TossPayment tossPayment, PaymentParams params) {
		// Empty 객체 생성 및 데이터 세팅
		Payment payment = new Payment();
		payment.setRsvIdx(params.getRsvIdx());
		payment.setPayMethod(tossPayment.getMethod());
		payment.setPayName(tossPayment.getOrderName());
		payment.setPayApproval(tossPayment.getCard().getApproveNo());
		payment.setPayAmount(tossPayment.getTotalAmount());
		
		return payment;
	}
}
