package com.edu.surfing.model.reservation;

import java.util.List;

import com.edu.surfing.domain.reservation.Reservation;

public interface ReservationDAO {
	public List selectAll();
	public List selectByMember(int memberIdx);
	public Reservation selectByIdx(int rsvIdx);
	public Reservation insert(Reservation reservation);
	public void delete(int rsvIdx);
}
