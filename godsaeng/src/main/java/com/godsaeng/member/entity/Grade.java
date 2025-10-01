package com.godsaeng.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GRADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRADENO")
    private Long gradeNo;
    
    @Column(name = "GRADENAME", length = 30, nullable = false)
    private String gradeName;
}
