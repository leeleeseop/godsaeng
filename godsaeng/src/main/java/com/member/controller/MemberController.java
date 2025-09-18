package com.member.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String join(@ModelAttribute Member member, Model model) {
        try {
            memberService.register(member);
            return "redirect:/member/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "member/join";
        }
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "member/login"; 
    }

    // 로그인 처리
//    @PostMapping("/login")
//    public String login(@RequestParam String memberId, @RequestParam String memberPw, HttpSession session, Model model) {
//        try {
//            Member loginMember = memberService.login(memberId, memberPw);
//            session.setAttribute("login", loginMember);
//            return "redirect:/"; 
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "member/login";
//        }
//    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 아이디 찾기 화면
    @GetMapping("/findId")
    public String findIdForm() {
        return "member/findId"; // findId.html
    }

    // 아이디 찾기 처리
    @PostMapping("/findId")
    public String findId(@RequestParam String memberName, @RequestParam String memberEmail, Model model) {
        try {
            Member member = memberService.findId(memberName, memberEmail);
            model.addAttribute("memberId", member.getMemberId());
            return "member/findIdResult"; // 아이디 결과 페이지
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "member/findId";
        }
    }

    // 비밀번호 재설정 화면
    @GetMapping("/resetPw")
    public String resetPwForm() {
        return "member/resetPw"; 
    }

    // 비밀번호 재설정 처리
    @PostMapping("/resetPw")
    public String resetPw(@RequestParam String memberId, @RequestParam String memberEmail, @RequestParam String newPw, Model model) {
        try {
            memberService.resetPassword(memberId, memberEmail, newPw);
            return "redirect:/member/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "member/resetPw";
        }
    }
}