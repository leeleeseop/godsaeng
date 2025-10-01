package com.godsaeng.member.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godsaeng.member.entity.Grade;
import com.godsaeng.member.entity.Member;
import com.godsaeng.member.repository.GradeRepository;
import com.godsaeng.member.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	
	// 모든 회원 조회
	public List<Member> getAllMember() {
		return memberRepository.findAll();
	}
	
	
	// 회원 번호 조회
	public Member getMemberById(Long memberNo) {
		return memberRepository.findById(memberNo).orElse(null);
	}
	
	
	// 회원 아이디 조회
	public Member getMemberMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId).orElse(null);
	}
	
	
	// 회원 이메일 조회
	public Member getMemberByEmail(String email) {
		return memberRepository.findByMemberEmail(email).orElse(null);
	}
	
	
	// 로그인 처리
	public Member login(String memberId, String memberPw) {
		Optional<Member> member = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
		if (member.isPresent()) {
			// 접속일 업데이트
			Member loginMember = member.get();
			loginMember.setMemberConDate(LocalDate.now());
			return memberRepository.save(loginMember);
		}
		return null;
	}
	
	
	// 회원가입
	public Member registMember(Member member) {
		// 아이디 중복 체크
		if (memberRepository.findByMemberId(member.getMemberId()).isPresent()) {
			throw new RuntimeException("이미 존재하는 아이디입니다.");
		}
		
		// 이메일 중복 체크
		if (memberRepository.findByMemberEmail(member.getMemberEmail()).isPresent()) {
			throw new RuntimeException("이미 존재하는 이메일입니다.");
		}
		
		// 기본 등급 설정 (user = 기본등급)
		if (member.getGrade() == null) {
			Grade userGrade = gradeRepository.findByGradeName("user");
			member.setGrade(userGrade);
		}
		
		// 기본값 설정
		member.setMemberRegDate(LocalDate.now());
		member.setMemberConDate(LocalDate.now());
		member.setMemberStatus("활성");
		member.setMemberNewMsgCnt(0);
		
		return memberRepository.save(member);
	}
	
	
	// 회원 정보 수정
	public Member updateMember(Member member) {
		return memberRepository.save(member);
	}
	
	
	// 회원 삭제
	// memberNo: 삭제할 회원 번호
	public void deleteMember(Long memberNo) {
		memberRepository.deleteById(memberNo);
	}
	
	
	// 상태별 회원 조회
	// status 회원 상태 ( 활성, 정지, 강퇴)의 해당 상태의 회원 리스트
	public List<Member> getMemberByStatus(String status) {
		return memberRepository.findByMemberStatus(status);
	}
	
	
	// 아이디 중복 검사
	// memberId 검사할 아이디 중복이면 true 사용 가능하면 false
	public boolean isIdDuplicate(String memberId) {
		return memberRepository.findByMemberEmail(memberId).isPresent();
	}
	
	
	// 이메일 중복 검사
	// email 검사할 이메일 중복이면 true 사용 가능하면 false
	public boolean isEmailDuplicate(String email) {
		return memberRepository.findByMemberEmail(email).isPresent();
	}
}
