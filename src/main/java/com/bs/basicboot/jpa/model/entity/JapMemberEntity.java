package com.bs.basicboot.jpa.model.entity;

import com.bs.basicboot.jpa.model.dto.JpaMember;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@SequenceGenerator(name = "seqJpamemberNo", sequenceName = "SEQ_JPAMEMBER_NO", allocationSize = 1)
public class JapMemberEntity {
    @Id
    @GeneratedValue(generator = "seqJpamemberNo", strategy = GenerationType.SEQUENCE)
    private Long memberNo;
    @Column(unique = true,nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private String userName;
    private LocalDate birthDay;
    private LocalDate reservationDay;
    private Integer age;

    public JpaMember toJpaMember() {
        return JpaMember.builder()
                .memberNo(memberNo)
                .userId(userId)
                .password(password)
                .name(userName)
                .birthDay(birthDay)
                .reservationDay(reservationDay)
                .age(age)
                .build();
    }

    public static JapMemberEntity fromJpaMember(JpaMember m) {
        return JapMemberEntity.builder()
                .memberNo(m.getMemberNo())
                .userId(m.getUserId())
                .password(m.getPassword())
                .userName(m.getName())
                .birthDay(m.getBirthDay())
                .reservationDay(m.getReservationDay())
                .age(m.getAge())
                .build();
    }
}
