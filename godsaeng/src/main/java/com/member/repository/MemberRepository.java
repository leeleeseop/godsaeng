package com.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	// 로그인: 아이디 존재 여부 확인
	Member findByMemberId(String memberId);
	
	// 아이디 찾기
	Member findByMemberNameAndMemberEmail(String memberName, String memberEmail);
	
	// 비밀번호 찾기
	Member findByMemberIdAndMemberEmail(String memberId, String memberEmail);
}
