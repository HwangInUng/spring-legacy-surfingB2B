package com.edu.surfing.model.business;

import java.util.List;

import com.edu.surfing.domain.business.ShopRegist;

public interface ShopRegistDAO {
	public List selectAll();
	public ShopRegist selectByIdx(int registIdx);
	public void insert(ShopRegist shopRegist);
	public void delete(int registIdx);
}
