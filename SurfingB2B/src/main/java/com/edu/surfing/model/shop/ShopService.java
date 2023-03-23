package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Shop;

public interface ShopService {
	public List getList();
	public Shop getDetail(int shopIdx);
	public Shop getDetailByBusiness(int businessIdx);
	public void regist(Shop shop, String savePath);
	public void edit(Shop shop);
	public void editWithImage(Shop shop, String savePath);
	public void remove(int shopIdx);
}
