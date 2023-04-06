package com.edu.surfing.model.reservation;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisReservationDAO implements ReservationDAO{
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Reservation.selectAll");
	}

	@Override
	public List selectByMember(int memberIdx) {
		return sqlSessionTemplate.selectList("Reservation.selectByMember", memberIdx);
	}

	@Override
	public Reservation selectByIdx(int rsvIdx) {
		return sqlSessionTemplate.selectOne("Reservation.selectByIdx", rsvIdx);
	}

	@Override
	public void insert(Reservation reservation) throws CustomException {
		int result = sqlSessionTemplate.insert("Reservation.insert", reservation);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "예약");
		}
	}

	@Override
	public void delete(int rsvIdx) throws CustomException {
		int result = sqlSessionTemplate.delete("Reservation.delete", rsvIdx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}
	
	
}
