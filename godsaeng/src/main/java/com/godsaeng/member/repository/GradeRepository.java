package com.godsaeng.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.godsaeng.member.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

	// 등급명으로 찾기
	Grade findByGradeName(String gradeName);
}
