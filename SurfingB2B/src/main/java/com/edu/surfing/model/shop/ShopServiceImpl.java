package com.edu.surfing.model.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.edu.surfing.model.util.FileManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
	private final ShopDAO shopDAO;
	private final ShopImageDAO shopImageDAO;
	private final FileManager fileManager;

	@Override
	public List getList() {
		return shopDAO.selectAll();
	}

	@Override
	public List getPopularShop() {
		return shopDAO.selectPopular();
	}

	@Override
	public Shop getDetail(int shopIdx) {
		return shopDAO.selectByIdx(shopIdx);
	}

	@Override
	public Shop getDetailByBusiness(int businessIdx) {
		return shopDAO.selectByBusinessIdx(businessIdx);
	}

	@Transactional
	@Override
	public void regist(Shop shop, String savePath) throws CustomException {
		// 파일명 가공 및 이미지명 리스트 반환
		MultipartFile[] files = shop.getImages();
		if (files.length != 0) {
			List<String> imageNameList = fileManager.getSaveFileName(files, savePath);

			// 첫번째 이미지는 대표이미지로 등록
			shop.setShopImage(imageNameList.get(0));
			// 매장 저장
			shopDAO.insert(shop);

			// 매장 이미지 저장
			for (int i = 0; i < imageNameList.size(); i++) {
				ShopImage shopImage = new ShopImage();
				String imageName = imageNameList.get(i);
				shopImage.setImageName(imageName);
				shopImage.setShop(shop);


				shopImageDAO.insert(shopImage);
			}
		} else {
			throw new CustomException(ErrorCode.FAILED_FILE_ERROR);
		}
	}

	@Override
	public void edit(Shop shop) throws CustomException {
		// 내용만 수정하기 때문에 별도 로직 불필요
		shopDAO.update(shop);
	}

	@Transactional(rollbackFor = CustomException.class)
	@Override
	public void editWithImage(Shop shop, String savePath) throws CustomException {
		int shopIdx = shop.getShopIdx();

		/*-------------------------------------------------------------
		 * 수정할 대상의 기존 이미지 호출
		 * 호출된 이미지명을 대상으로 경로상 파일 삭제
		 * 삭제 완료 후 DB 이미지명 삭제
		 * 모든 작업이 완료되면 update 수행
		 -------------------------------------------------------------*/
		List<String> shopImageList = shopImageDAO.selectByShop(shopIdx);
		MultipartFile[] files = shop.getImages();
		if (files.length != 0) {
			if (fileManager.removeImage(shopImageList, savePath)) {
				// 삭제 완료 후 DB 삭제
				shopImageDAO.deleteByShop(shopIdx);

				// 새로 생성된 이미지명 대입
				shopImageList = fileManager.getSaveFileName(files, savePath);

				for (int i = 0; i < shopImageList.size(); i++) {
					ShopImage shopImage = new ShopImage();
					String imageName = shopImageList.get(i);

					shopImage.setImageName(imageName);
					shopImage.setShop(shop);

					if (i == 0) {
						shop.setShopImage(imageName);
					}
					shopImageDAO.insert(shopImage);
				}

				shopDAO.update(shop);
			}
		} else {
			throw new CustomException(ErrorCode.FAILED_FILE_ERROR);
		}
	}

	@Override
	public void remove(int shopIdx) throws CustomException {
	}

}
