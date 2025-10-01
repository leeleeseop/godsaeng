package com.godsaeng.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.godsaeng.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	// 아이디로 찾기
	Optional<Member> findByMemberId(String memberId);
	
	// 이메일로 찾기
    Optional<Member> findByMemberEmail(String memberEmail);
    
    // 상태별로 찾기
    List<Member> findByMemberStatus(String memberStatus);
    
    // 등급별로 찾기
    List<Member> findByGrade_GradeNo(Long gradeNo);
    
    // 로그인용 - 아이디와 비밀번호로 찾기
    @Query("SELECT m FROM Member m WHERE m.memberId = :memberId AND m.memberPw = :memberPw")
    Optional<Member> findByMemberIdAndMemberPw(@Param("memberId") String memberId, @Param("memberPw") String memberPw);
}
