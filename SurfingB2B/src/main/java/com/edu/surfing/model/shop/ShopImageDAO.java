package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.ShopImage;

public interface ShopImageDAO {
	public List<String> selectByShop(int shopIdx);
	public void insert(ShopImage shopImage);
	public void deleteByShop(int shopIdx);
}
