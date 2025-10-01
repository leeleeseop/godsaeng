package com.godsaeng.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godsaeng.member.entity.Grade;
import com.godsaeng.member.repository.GradeRepository;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	// 모든 등급 조회
	public List<Grade> getAllgrades() {
		return gradeRepository.findAll();
	}
	
	// 등급명으로 조회
	public Grade getGradeName(String gradeName) {
		return gradeRepository.findByGradeName(gradeName);
	}
}
