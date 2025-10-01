package com.godsaeng.member.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.godsaeng.member.entity.Member;
import com.godsaeng.member.service.MemberService;
import com.godsaeng.member.service.GradeService;
import java.time.LocalDate;

/**
 * Service 레이어 기능을 테스트하기 위한 컨트롤러
 * - 개발 단계에서 Service 클래스의 메서드들이 정상 작동하는지 확인
 * - 실제 운영에서는 사용하지 않는 테스트용 API들을 제공
 * - URL 패턴: /service-test/**
 */
@RestController
@RequestMapping("/service-test")
public class ServiceTestController {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private GradeService gradeService;
    
    /**
     * 로그인 기능 테스트 API
     * @param id 로그인할 아이디
     * @param pw 로그인할 비밀번호
     * @return 로그인 성공한 회원 정보 또는 null
     */
    @GetMapping("/login/{id}/{pw}")
    public Member testLogin(@PathVariable("id") String id, @PathVariable("pw") String pw) {
        return memberService.login(id, pw);
    }
    
    /**
     * 회원가입 기능 테스트 API
     * 미리 정의된 테스트 회원을 생성
     * @return 생성된 회원 정보 또는 null (중복 시)
     */
    @GetMapping("/register")
    public Member testRegister() {
        Member member = new Member();
        member.setMemberId("servicetest");
        member.setMemberPw("1234");
        member.setMemberName("서비스테스트");
        member.setMemberBirth(LocalDate.of(1995, 5, 15));
        member.setMemberEmail("servicetest@test.com");
        
        try {
            return memberService.registMember(member);
        } catch (RuntimeException e) {
            return null; // 중복일 경우
        }
    }
    
    /**
     * 아이디 중복 검사 테스트 API
     * @param id 검사할 아이디
     * @return 중복이면 true, 사용 가능하면 false
     */
    @GetMapping("/check-id/{id}")
    public boolean checkIdDuplicate(@PathVariable("id") String id) {
        return memberService.isIdDuplicate(id);
    }
}