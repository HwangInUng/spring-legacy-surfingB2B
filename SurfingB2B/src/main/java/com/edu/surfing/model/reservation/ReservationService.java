package com.edu.surfing.model.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.edu.surfing.domain.reservation.Payment;
import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.domain.reservation.TossPayment;

@Slf4j
@PropertySource("/WEB-INF/config/api.properties")
@RequiredArgsConstructor
@Service
public class ReservationService {
	private final TossClient tossClient;
	
	public Reservation requestConfirmPayment(PaymentParams paymentParams) {
		TossPayment tossPayment = tossClient.getConfirmPayment(paymentParams);
		log.debug("반환받은 tossPayment:: " + tossPayment);
		
		Reservation reservation = new Reservation();
		
		return null;
	}
}
