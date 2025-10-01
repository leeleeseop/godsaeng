package com.godsaeng.member.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godsaeng.member.entity.Grade;
import com.godsaeng.member.entity.Member;
import com.godsaeng.member.repository.GradeRepository;
import com.godsaeng.member.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/test")
@Log4j2
public class MemberTestController {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    // 모든 회원 조회
    @GetMapping("/members")
    public List<Member> getAllMembers() {
    	log.info("실행지점");
        return memberRepository.findAll();
    }
    
    // 모든 등급 조회
    @GetMapping("/grades")
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    
    // 특정 회원 조회
    @GetMapping("/member/{member_Id}")
    public Member getMemberById(@PathVariable("member_Id") String member_Id) {
        return memberRepository.findByMemberId(member_Id).orElse(null);
    }
    
    // 새 회원 생성 테스트
    @PostMapping("/member")
    public Member createMember() {
        Grade userGrade = gradeRepository.findByGradeName("user");
        
        Member member = new Member();
        member.setMemberId("testuser");
        member.setMemberPw("testpw");
        member.setMemberName("테스트유저");
        member.setMemberBirth(LocalDate.of(1990, 1, 1));
        member.setMemberEmail("test@test.com");
        member.setGrade(userGrade);
        
        return memberRepository.save(member);
    }
}
