package com.bs.basicboot.jpa.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaMember {
    private Long memberNo;
    @NotEmpty
    @Size(min = 4, message = "4글자이상 입력하세요.")
    private String userId;
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[a-zA-Z]).{5,}",message = "비밀번호 규칙에 위배됩니다. 숫자, 영문자 5글자로 설정")
    private String password;
    @Size(min = 2, message = "2글자이상 입력하세요.")
    private String userName;
    @PastOrPresent(message = "과거나 오늘로 설정")
    private LocalDate birthDay;
    @Future(message = "오늘 후의 일자를 선택하세요")
    private LocalDate reservationDay;
    @Positive
    @Min(value = 19, message = "성인만 가능")
    private Integer age;
}
