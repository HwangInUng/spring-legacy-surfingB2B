package com.edu.surfing.model.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.ShopException;
import com.edu.surfing.exception.ShopImageException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.util.FileManager;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDAO shopDAO;
	@Autowired
	private ShopImageDAO shopImageDAO;
	@Autowired
	private FileManager fileManager;
	
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
		//파일 저장
		fileManager.save(shop, savePath);
		
		//매장 저장
		shopDAO.insert(shop);
		
		//매장 이미지 저장
		List<ShopImage> imageList = shop.getImageList();
		for(ShopImage image : imageList) {
			image.setShop(shop);
			shopImageDAO.insert(image);
		}
	}

	@Override
	public void edit(Shop shop) {
	}

	@Override
	public void remove(int shopIdx) {
	}
	
}
