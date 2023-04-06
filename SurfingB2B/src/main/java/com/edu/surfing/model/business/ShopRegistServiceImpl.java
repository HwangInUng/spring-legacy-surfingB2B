package com.edu.surfing.model.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.surfing.domain.business.ShopRegist;
import com.edu.surfing.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopRegistServiceImpl implements ShopRegistService{
	private final ShopRegistDAO shopRegistDAO;

	@Override
	public List getList() {
		return shopRegistDAO.selectAll();
	}

	@Override
	public ShopRegist getDetail(int registIdx) {
		return shopRegistDAO.selectByIdx(registIdx);
	}

	@Override
	public void regist(ShopRegist shopRegist) throws CustomException {
		shopRegistDAO.insert(shopRegist);
	}

	@Override
	public void remove(int registIdx) throws CustomException {
		shopRegistDAO.delete(registIdx);
	}
	
	
}
