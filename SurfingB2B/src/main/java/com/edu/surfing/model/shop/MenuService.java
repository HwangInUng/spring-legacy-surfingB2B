package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Menu;

public interface MenuService {
	public List<Menu> getList(int shopIdx);
	public Menu getDetail(int menuIdx);
	public void regist(Menu menu, String savePath);
	public void edit(Menu menu);
	public void remove(int menuIdx, String savePath);
	public void removeByShop(int shopIdx);
}
