package com.edu.surfing.model.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Trainer;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.model.util.FileManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
	private final TrainerDAO trainerDAO;
	private final FileManager fileManager;

	@Override
	public List<Trainer> getList() {
		return trainerDAO.selectAll();
	}

	@Override
	public Trainer getDetail(int trainerIdx) {
		return trainerDAO.selectById(trainerIdx);
	}

	@Transactional
	@Override
	public void regist(Trainer trainer, String savePath) throws CustomException {
		//파일추출
		MultipartFile file = trainer.getProfile();
		
		//사진등록
		String trainerImage = fileManager.getSaveFileName(file, savePath);
		trainer.setTrainerImage(trainerImage);
		
		//강사등록
		trainerDAO.insert(trainer);
	}

	@Override
	public void edit(Trainer trainer) throws CustomException {

	}

	@Override
	public void remove(int trainerIdx) throws CustomException {

	}

}
