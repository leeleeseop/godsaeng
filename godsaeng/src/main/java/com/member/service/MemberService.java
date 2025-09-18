package com.member.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.entity.Member;
import com.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	// 회원가입
	// member에 있는 회원정보를 입력해서 member에 저장
	public Member register(Member member) {
		// 비밀번호 암호화
		member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
		return memberRepository.save(member);
	}
	
	// 로그인
	public Member login(String memberId, String memberPw) {
		Member member = memberRepository.findByMemberId(memberId);
		
		// member가 null이면 아이디가 존재하지 않음 
		if(member == null) {
			throw new RuntimeException("아이디가 존재하지 않습니다.");
		}
		
		// 입력한 비밀번호와 원래 비밀번호랑 비교
		if(!passwordEncoder.matches(memberPw, member.getMemberPw())) {
		    throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return member;
	}
	
	// 아이디 찾기
	public Member findId(String name, String email) {
		Member member = memberRepository.findByMemberNameAndMemberEmail(name, email);
		
		if (member == null) {
			throw new RuntimeException("일치하는 회원 정보가 없습니다.");
		}
		
		return member;
	}
	
	public void resetPassword(String memberId, String email, String newPw) {
		Member member = memberRepository.findByMemberIdAndMemberEmail(memberId, memberId);
		
		if (member == null) {
			throw new RuntimeException("아이디 또는 이메일이 일치하지 않습니다.");
		}
		
		// 새로운 비밀번호로 저장
		member.setMemberPw(newPw);
		memberRepository.save(member);
	}
	
	// 회원 상태 변경 (강퇴, 정지, 활성화)
	public void updateStatus(Long memberNo, String status) {
		Member member = memberRepository.findById(memberNo).orElse(null);
		
		if (member == null) {
			throw new RuntimeException("회원이 존재하지 않습니다");
		}
		
		member.setMemberStatus(status);
		memberRepository.save(member);
	}
}
