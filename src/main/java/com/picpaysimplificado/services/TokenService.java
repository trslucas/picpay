package com.picpaysimplificado.services;


import com.picpaysimplificado.domain.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "pGHoCRuw2mLT9n2ED8TyPjzDaDA1VnTOkNf+2EHDL5A=";

    public String generateToken(User user) {
        return Jwts.builder().setSubject(String.valueOf(user.getId())).setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getSubject(String token) throws Exception {

        try {


        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (ExpiredJwtException e) {
            throw new Exception("Token expirado");
        } catch (UnsupportedJwtException e) {
           throw new Exception("Token não suportado");
        } catch (MalformedJwtException e) {
           throw new Exception("Token malformado");
        } catch (SignatureException e) {
           throw new Exception("Assinatura inválida do token");
        } catch (IllegalArgumentException e) {
           throw new Exception("Token vazio ou inválido");
        }
    }
}
