package com.bs.basicboot.common.config.token;


import com.bs.basicboot.jpa.model.dao.JpaMemberRepository;
import com.bs.basicboot.jpa.model.dto.JpaMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
//@RequiredArgsConstructor
public class JwtTokenUtils {
//    @Value("${token.issuer}")
    private final String issuer="bsyoo";//생성자를 설정하는 값
    //토큰을 생성하는데 사용하는 key
    private final SecretKey key= Jwts.SIG.HS512.key().build();

    @Autowired
    private JpaMemberRepository repository;

    public String generateToken(JpaMember m) {
        return createToken(m,Duration.ofSeconds(5));
    }

    //저장할 사용자 정보와 토큰유효기간을 외부에서 설정할 수 있게 매개변수로 처리
    private String createToken(JpaMember m, Duration exp) {
        Date limit=new Date(new Date().getTime()+exp.toMillis());
        return Jwts.builder()
                .header().add(Map.of("type","jwt")).and()
                .issuer(this.issuer)
                .signWith(this.key)//토큰생성키
                .expiration(limit)//유효기간
                .claims(Map.of("id",m.getUserId(),"memberNo",m.getMemberNo()))
                .compact();
    }
    //토큰으로 인증을 처리하는 메소드
    public Authentication getAuthentication(String token) {
        String userId = getUserId(token);
        JpaMember member = repository.findByUserId(userId).orElseThrow(() -> {
            throw new BadCredentialsException("인증실패");
        }).toJpaMember();
        return new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getAuthorities());
    }

    //토큰의 율효성을 확인하는 메소드
    public boolean  validToToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //토큰의 cliamws에 저장된 id 값을 가져오는 메솓,.
    public String getUserId(String token) {
        Claims claims=getClaims(token);
        return claims.get("id",String.class);
    }
    //토큰의 claims를 가져오는 메소드
    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
