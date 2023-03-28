package com.edu.surfing.model.reservation;

import com.edu.surfing.domain.reservation.Payment;

public interface PaymentDAO {
	public Payment selectByReservation(int rsvIdx);
	public void insert(Payment payment);
	public void delete(int payIdx);
}
