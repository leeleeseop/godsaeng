package com.godsaeng.member.entity;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;
    
    @Column(name = "MEMBER_ID", length = 20, unique = true)
    private String memberId;
    
    @Column(name = "MEMBER_PW", length = 200, nullable = false)
    private String memberPw;
    
    @Column(name = "MEMBER_NAME", length = 30, nullable = false)
    private String memberName;
    
    @Column(name = "MEMBER_BIRTH", nullable = false)
    private LocalDate memberBirth;
    
    @Column(name = "MEMBER_EMAIL", length = 100, nullable = false, unique = true)
    private String memberEmail;
    
    @Column(name = "MEMBER_REGDATE")
    private LocalDate memberRegDate;
    
    @Column(name = "MEMBER_CONDATE")
    private LocalDate memberConDate;
    
    @Column(name = "MEMBER_STATUS", length = 10)
    private String memberStatus = "활성";
    
    @Column(name = "MEMBER_NEWMSGCNT")
    private Integer memberNewMsgCnt = 0;
    
    @Column(name = "MEMBER_PHOTO", length = 100)
    private String memberPhoto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GRADENO")
    private Grade grade;
    
    @PrePersist
    protected void onCreate() {
        memberRegDate = LocalDate.now();
        memberConDate = LocalDate.now();
    }
}
