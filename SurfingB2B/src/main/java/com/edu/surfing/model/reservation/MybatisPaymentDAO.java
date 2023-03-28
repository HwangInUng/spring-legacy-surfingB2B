package com.edu.surfing.model.reservation;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.reservation.Payment;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MybatisPaymentDAO implements PaymentDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Payment selectByReservation(int rsvIdx) {
		return sqlSessionTemplate.selectOne("Payment.selectByReservation", rsvIdx);
	}
	
	@Override
	public void insert(Payment payment) throws CustomException {
		int result = sqlSessionTemplate.insert("Payment.insert", payment);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "결제");
		}
	}

	@Override
	public void delete(int payIdx) throws CustomException {
		int result = sqlSessionTemplate.delete("Payment.delete", payIdx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}
}
