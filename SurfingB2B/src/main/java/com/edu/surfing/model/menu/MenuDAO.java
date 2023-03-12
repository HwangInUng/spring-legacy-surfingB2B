package com.edu.surfing.model.menu;

import java.util.List;

import com.edu.surfing.domain.menu.Menu;

public interface MenuDAO {
	public List<Menu> selectAll();
	public Menu selectByShop(int shop_idx);
	public void insert(Menu menu);
	public void update(Menu menu);
	public void delete(int menu_idx);
	public void deleteByShop(int shop_idx);
}
