package com.edu.surfing.model.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Menu;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.model.util.FileManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
	private final MenuDAO menuDAO;
	private final FileManager fileManager;
	
	@Override
	public List<Menu> getList(int shopIdx) {
		return menuDAO.selectAll(shopIdx);
	}

	@Override
	public Menu getDetail(int menuIdx) {
		return menuDAO.select(menuIdx);
	}

	@Transactional
	@Override
	public void regist(Menu menu, String savePath) throws CustomException {
		//파일 및 경로 추출
		MultipartFile file = menu.getImage();
		String imageName = fileManager.getSaveFileName(file, savePath);
		
		//이미지명 주입 및 상품등록
		menu.setMenuImage(imageName);
		menuDAO.insert(menu);
	}

	@Override
	public void edit(Menu menu) throws CustomException {
		
	}

	@Override
	public void remove(int meunIdx, String savePath) throws CustomException {
		Menu menu = menuDAO.select(meunIdx);
		
		if(fileManager.removeImage(menu.getMenuImage(), savePath)) {
			menuDAO.delete(meunIdx);
		}
	}

	@Override
	public void removeByShop(int shopIdx) throws CustomException {
		
	}

}
