package com.uu.mircroservice.core.jwt;

import com.uu.mircroservice.core.config.Constants;
import com.uu.mircroservice.core.exception.ResponseCodeException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.Date;
import com.uu.mircroservice.core.config.Error;
@Slf4j
public class JwtConfig {
    private static final String KEY = "bDRpNTqfRZXnqVNBWNOf1C";
    private static final long EXPIRED_TIME = 1000L;

    public static String generateToken(JwtTokenData user) {
        if (user == null) throw Constants.invalidInput;
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiryDate = now.plusMinutes(EXPIRED_TIME);
        return Jwts.builder()
                .setSubject(user.getId() + "")
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(expiryDate.toInstant()))
                .claim("username", user.getUsername())
                .claim("shopId", user.getShopId())
                .claim("role", user.getRole())
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }

    public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException ex) {
            log.error("Invalid JWT signature");
            throw new ResponseCodeException(Error.TOKEN_NOT_VALID);
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ResponseCodeException(Error.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new ResponseCodeException(Error.TOKEN_NOT_VALID);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new ResponseCodeException(Error.TOKEN_NOT_VALID);
        }
    }

    public static JwtTokenData getTokenData(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
        JwtTokenData tokenData = new JwtTokenData();
        tokenData.setId(Integer.valueOf(claims.getSubject()));
        tokenData.setUsername(claims.get("username", String.class));
        tokenData.setRole(claims.get("role", String.class));
        tokenData.setShopId(claims.get("shopId", Integer.class));
        return tokenData;
    }
}
