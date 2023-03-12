package com.edu.surfing.model.menu;

import java.util.List;

import com.edu.surfing.domain.menu.Menu;

public interface MenuService {
	public List<Menu> getList();
	public Menu getDetailByShop(int shop_idx);
	public void regist(Menu menu, String savePath);
	public void edit(Menu menu);
	public void remove(int menu_idx);
	public void removeByShop(int shop_idx);
}
