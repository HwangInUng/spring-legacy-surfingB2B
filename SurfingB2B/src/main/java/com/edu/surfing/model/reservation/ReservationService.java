package com.edu.surfing.model.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.edu.surfing.domain.reservation.Payment;
import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.domain.reservation.TossPayment;
import com.edu.surfing.exception.CustomException;

@Slf4j
@PropertySource("/WEB-INF/config/api.properties")
@RequiredArgsConstructor
@Service
public class ReservationService {
	private final ReservationDAO reservationDAO;
	private final PaymentDAO paymentDAO;
	private final TossClient tossClient;
	
	//예약등록
	public Reservation registReservation(Reservation reservation) throws CustomException {
		reservationDAO.insert(reservation);
		
		log.debug("반환되는 예약번호는 :: " + reservation.getRsvIdx());
		return reservation;
	}
	
	//예약에 대한 결제 승인요청 메소드
	public Reservation requestConfirmPayment(PaymentParams paymentParams) throws CustomException {
		Payment payment = tossClient.getConfirmPayment(paymentParams);
		log.debug("반환받은 Payment:: " + payment);
		
		//예약 객체 저장
		paymentDAO.insert(payment);
		
		//저장 후 예약정보 조회 및 결제정보 주입
		Reservation reservation = reservationDAO.selectByIdx(paymentParams.getRsvIdx());
		reservation.setPayment(payment);
		
		return reservation;
	}
}
