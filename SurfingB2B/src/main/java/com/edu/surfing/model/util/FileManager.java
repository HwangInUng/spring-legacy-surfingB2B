package com.edu.surfing.model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManager {

	public String createFilename(String filename) {
		long time = System.currentTimeMillis();
		String ext = filename.substring(filename.lastIndexOf("."));

		return time + ext;
	}

	// 복수 파일을 저장
	public List getSaveFileName(MultipartFile[] files, String savePath) throws CustomException {
		List<String> imageNameList = new ArrayList(); // 가공된 이미지명을 저장
		
			try {
				for (MultipartFile file : files) {
					Thread.sleep(10); // 파일명 중복 방지
					String filename = file.getOriginalFilename();
					filename = createFilename(filename);

					file.transferTo(new File(savePath + filename));
					imageNameList.add(filename); // 가공된 이미지명을 List에 추가
				}
			} catch (IllegalStateException | IOException | InterruptedException e) {
				/* checked 발생 시 wrapping하여 서비스로 전달 */
				throw new CustomException(ErrorCode.INTERNAL_FILE_ERROR);
			}
		return imageNameList;
	}

	// 하나의 파일 저장
	public String getSaveFileName(MultipartFile file, String savePath) throws CustomException {
		String imageName = null; // 가공된 이미지명을 저장할 변수

		try {
			imageName = file.getOriginalFilename(); // 파일 이름 추출
			imageName = createFilename(imageName); // 추출된 이름으로 새로운 파일명 조합

			file.transferTo(new File(savePath + imageName));
		} catch (IllegalStateException | IOException e) {
			throw new CustomException(ErrorCode.INTERNAL_FILE_ERROR);
		}
		return imageName;
	}

	// 기존에 저장된 이미지 삭제
	public Boolean removeImage(List<String> imageList, String savePath) throws CustomException {

		try {
			for (String image : imageList) {
				log.debug("삭제 대상 파일명 :: " + image);
				String path = savePath + image;
				File file = new File(path);
				file.delete();
			}
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_FILE_DELETE_ERROR);
		}
		return true;
	}

	// 기존에 저장된 이미지 삭제
	public Boolean removeImage(String image, String savePath) throws CustomException {

		try {
			log.debug("삭제 대상 파일명 :: " + image);
			String path = savePath + image;
			File file = new File(path);
			file.delete();
		} catch (Exception e) {
			throw new CustomException(ErrorCode.INTERNAL_FILE_DELETE_ERROR);
		}
		return true;
	}
}