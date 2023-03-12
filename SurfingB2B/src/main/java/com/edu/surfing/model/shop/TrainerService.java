package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Trainer;

public interface TrainerService {
	public List<Trainer> getList();
	public Trainer getDetail(int trainerIdx);
	public void regist(Trainer trainer, String savePath);
	public void edit(Trainer trainer);
	public void remove(int trainerIdx);
}
