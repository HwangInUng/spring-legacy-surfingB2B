package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Shop;

public interface ShopDAO {
	public List<Shop> selectAll();
	public Shop selectById(int shopIdx);
	public void insert(Shop shop);
	public void update(Shop shop);
	public void delete(int shopIdx);
}
