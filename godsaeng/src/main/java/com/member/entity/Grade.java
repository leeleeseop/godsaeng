package com.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gradeno")
    private Integer gradeNo;

    @Column(name = "gradename", nullable = false, length = 30)
    private String gradeName;

    // equals, hashCode는 PK만 기준으로
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return gradeNo != null && gradeNo.equals(grade.gradeNo);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
