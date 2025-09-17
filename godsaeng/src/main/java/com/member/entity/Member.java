package com.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "member_id", nullable = false, unique = true, length = 20)
    private String memberId;

    @Column(name = "member_pw", nullable = false, length = 200)
    private String memberPw;

    @Column(name = "member_name", nullable = false, length = 30)
    private String memberName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "member_birth", nullable = false)
    private LocalDate memberBirth;

    @Column(name = "member_email", nullable = false, unique = true, length = 100)
    private String memberEmail;

    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "member_regdate")
    private LocalDateTime memberRegDate = LocalDateTime.now();

    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "member_condate")
    private LocalDateTime memberConDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "member_status", length = 10)
    private String memberStatus = "활성";

    @Builder.Default
    @Column(name = "member_newmsgcnt")
    private Integer memberNewMsgCnt = 0;

    @Column(name = "member_photo", length = 100)
    private String memberPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gradeNo", nullable = false)
    private Grade grade;

    // equals, hashCode는 PK만 기준으로
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return memberNo != null && memberNo.equals(member.memberNo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
