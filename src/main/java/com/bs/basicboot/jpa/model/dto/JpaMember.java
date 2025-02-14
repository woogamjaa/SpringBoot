package com.bs.basicboot.jpa.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaMember implements UserDetails {
    private Long memberNo;
    @NotEmpty
    @Size(min = 4, message = "4글자이상 입력하세요.")
    private String userId;
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[a-zA-Z]).{5,}",message = "비밀번호 규칙에 위배됩니다. 숫자, 영문자 5글자로 설정")
    private String password;
    @Size(min = 2, message = "2글자이상 입력하세요.")
    private String name;
    @PastOrPresent(message = "과거나 오늘로 설정")
    private LocalDate birthDay;
    @Future(message = "오늘 후의 일자를 선택하세요")
    private LocalDate reservationDay;
    @Positive
    @Min(value = 19, message = "성인만 가능")
    private Integer age;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth=new ArrayList<>();
        if (userId.equals("admin"))
            auth.add(new SimpleGrantedAuthority("admin"));
        auth.add(new SimpleGrantedAuthority("user"));
        return auth;
    }

    @Override
    public String getUsername() {
        return userId;
    }
}
