package com.edu.surfing.model.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.ShopException;
import com.edu.surfing.exception.ShopImageException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.model.util.FileManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
	private final ShopDAO shopDAO;
	private final ShopImageDAO shopImageDAO;
	private final FileManager fileManager;
	
	@Override
	public List getList() {
		return null;
	}

	@Override
	public Shop getDetail(int shopIdx) {
		return null;
	}

	@Transactional(rollbackFor = {ShopException.class, ShopImageException.class, UploadException.class})
	@Override
	public void regist(Shop shop, String savePath) throws ShopException, ShopImageException, UploadException{
		//파일명 가공 및 이미지명 리스트 반환
		MultipartFile[] files = shop.getImages();
		List<String> imageNameList = fileManager.getSaveFileName(files, savePath);
		
		//매장 저장
		shopDAO.insert(shop);
		
		//매장 이미지 저장
		for(int i = 0; i < imageNameList.size(); i++) {
			ShopImage shopImage = new ShopImage();
			String imageName = imageNameList.get(i);
			shopImage.setImageName(imageName);
			shopImage.setShop(shop);
			
			shopImageDAO.insert(shopImage);
		}
	}

	@Override
	public void edit(Shop shop) {
	}

	@Override
	public void remove(int shopIdx) {
	}
	
}
