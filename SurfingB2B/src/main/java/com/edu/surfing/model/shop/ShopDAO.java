package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Shop;

public interface ShopDAO {
	public List<Shop> selectAll();
	public Shop selectByIdx(int shopIdx);
	public Shop selectByBusinessIdx(int businessIdx);
	public List<Shop> selectBySpot(String shopTown);
	public List<Shop>selectPopular();
	public void insert(Shop shop);
	public void update(Shop shop);
	public void delete(int shopIdx);
}
