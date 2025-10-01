package com.godsaeng.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godsaeng.member.entity.Member;
import com.godsaeng.member.service.GradeService;
import com.godsaeng.member.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GradeService gradeService;
	
	// 로그인 페이지 표시
	@GetMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	
	
	// 로그인 처리
	@PostMapping("/login")
	public String login (@RequestParam("memberId") String memberId, @RequestParam("memberPw") String memberPw, HttpSession session, Model model) {
		Member member = memberService.login(memberId, memberPw);
		
		if (member != null) {
			// 로그인 성공
			session.setAttribute("loginMember", member);
			return "redirect:/";
		} else {
			// 로그인 실패
			model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다");
			return "member/login";
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	// 회원가입 표시
	@GetMapping("/register")
	public String registerForm() {
		return "member/register";
	}
	
	// 회원가입 처리 - 입력 받은 정보로 새 회원을 등록
	@PostMapping("/register")
	public String register(@ModelAttribute Member member, Model model) {
		try {
			memberService.registMember(member);
			// 회원가입 성공
			model.addAttribute("success", "회원가입이 완료되었습니다. 로그인해서주세요");
			return "redirect:/member/login";
		} catch (RuntimeException e) {
			// 회원가입 실패 (중복 등)
			model.addAttribute("error", e.getMessage());
			return "member/register";
		}
	}
	
	// 아이디 중복 체크
	@GetMapping("/check-id")
	@ResponseBody
	public boolean checkId(@RequestParam("memberId") String memberId) {
		return memberService.isIdDuplicate(memberId);
	}
	
	
	
	
}
