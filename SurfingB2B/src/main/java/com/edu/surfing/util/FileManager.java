package com.edu.surfing.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.UploadException;

@Component
public class FileManager {

	public String createFilename(String filename) {
		long time = System.currentTimeMillis();
		String ext = filename.substring(filename.lastIndexOf("."));

		return time + ext;
	}

	public void save(Shop shop, String savePath) throws UploadException {
		MultipartFile[] files = shop.getImages(); //파일 추출
		
		List<ShopImage> shopImageList = new ArrayList(); //shopImage 리스트 인스턴스 생성
		shop.setImageList(shopImageList); //shopImage 리스트 주입
		
		try {
			for (MultipartFile file : files) {
				Thread.sleep(10); //파일명 중복 방지
				ShopImage shopImage = new ShopImage(); //empty pimg 객체 생성
				
				String filename = file.getOriginalFilename();
				filename = createFilename(filename);
				
				file.transferTo(new File(savePath + filename));
				shopImage.setImageName(filename); //파일명 세팅
				
				shopImageList.add(shopImage); //파일명 세팅된 객체 저장
			}
		} catch (IllegalStateException | IOException | InterruptedException e) {
			e.printStackTrace();
			throw new UploadException("저장 실패", e);
		}
	}
}