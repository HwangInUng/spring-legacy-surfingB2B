package com.edu.surfing.model.business;

import java.util.List;

import com.edu.surfing.domain.business.ShopRegist;

public interface ShopRegistService {
	public List getList();
	public ShopRegist getDetail(int registIdx);
	public void regist(ShopRegist shopRegist);
	public void remove(int registIdx);
}
