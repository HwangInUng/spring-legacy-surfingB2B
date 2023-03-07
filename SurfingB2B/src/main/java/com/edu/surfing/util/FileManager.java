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

	public List save(MultipartFile[] files, String savePath) throws UploadException {
		List<String> imageNameList = new ArrayList(); //가공된 이미지명을 저장
		
		try {
			for (MultipartFile file : files) {
				Thread.sleep(10); //파일명 중복 방지
				String filename = file.getOriginalFilename();
				filename = createFilename(filename);
				
				file.transferTo(new File(savePath + filename));
				imageNameList.add(filename); //가공된 이미지명을 List에 추가
			}
		} catch (IllegalStateException | IOException | InterruptedException e) {
			e.printStackTrace();
			throw new UploadException("저장 실패", e);
		}
		return imageNameList;
	}
}