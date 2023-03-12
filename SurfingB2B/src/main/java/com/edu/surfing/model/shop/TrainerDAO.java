package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Trainer;

public interface TrainerDAO {
	public List<Trainer> selectAll();
	public Trainer selectById(int trainerIdx);
	public void insert(Trainer trainer);
	public void update(Trainer trainer);
	public void delete(int trainerIdx);
}
