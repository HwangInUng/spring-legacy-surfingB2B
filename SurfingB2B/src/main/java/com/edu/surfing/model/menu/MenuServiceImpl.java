package com.edu.surfing.model.menu;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.menu.Menu;
import com.edu.surfing.exception.MenuException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.model.util.FileManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
	private final MenuDAO menuDAO;
	private final FileManager fileManager;
	
	@Override
	public List<Menu> getList() {
		return menuDAO.selectAll();
	}

	@Override
	public Menu getDetailByShop(int shop_idx) {
		return menuDAO.selectByShop(shop_idx);
	}

	@Transactional(rollbackFor = {MenuException.class, UploadException.class})
	@Override
	public void regist(Menu menu, String savePath) throws MenuException, UploadException {
		//파일 및 경로 추출
		MultipartFile file = menu.getImage();
		String imageName = fileManager.getSaveFileName(file, savePath);
		
		//이미지명 주입 및 상품등록
		menu.setMenuImage(imageName);
		menuDAO.insert(menu);
	}

	@Override
	public void edit(Menu menu) throws MenuException {
		
	}

	@Override
	public void remove(int menu_idx) throws MenuException {
		
	}

	@Override
	public void removeByShop(int shop_idx) throws MenuException {
		
	}

}
