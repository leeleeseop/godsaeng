package com.member.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.member.entity.Member;
import com.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	// 회원가입 화면
	@GetMapping("/join")
	public String joinForm() {
		return "member/join";
	}
	
	// 회원가입 처리
	@PostMapping("/join")
	public String join(Member member) {
		memberService.register(member);
		return "member/login";
	}
	
	// 로그인 화면
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
	// 로그인 처리
	@PostMapping("/login")
	public String login(@RequestParam String memberId, @RequestParam String memberPw, HttpSession session) {
		Member loginMember = memberService.login(memberId, memberPw);
		session.setAttribute("login", loginMember);
		return "redirect:/";
	}
	
	
}
