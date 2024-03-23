package com.Authentication.Auth.Services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.Authentication.Auth.Entities.UserInfo;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {
     private final String SecretKey = "96E0AFB1EF27B847BD338CABF3E20CB6BD53F3CE2ED0F6AE6AC3AC8C5C16E711";

     public String extractEmail(String token){
            return extractClaim(token,Claims::getSubject);
     }

     private boolean isTokenExpired(String token){
         return extractExpiration(token).before(new Date());
     }
     public boolean isValid(String token, UserInfo userInfo){
         return userInfo.getEmail().equals(extractEmail(token)) && !isTokenExpired(token);
     }

     private Date extractExpiration(String token){

         return extractClaim(token,Claims::getExpiration);
     }
     public <T> T extractClaim(String token, Function<Claims,T> resolver){
         Claims claims = ExtractAllClaims(token);
         return resolver.apply(claims);
     }
     private Claims ExtractAllClaims(String token){
                return Jwts.
                        parser()
                        .verifyWith(SignWithKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
     }
     public String getJWTToken(UserInfo userInfo){
         String Token = Jwts.builder()
                 .subject(userInfo.getEmail())
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                 .signWith(SignWithKey())
                 .compact();

         return Token;
     }

     public javax.crypto.SecretKey SignWithKey(){
         byte[]keyBytes = Decoders.BASE64.decode(SecretKey);
         return Keys.hmacShaKeyFor(keyBytes);
     }
}
