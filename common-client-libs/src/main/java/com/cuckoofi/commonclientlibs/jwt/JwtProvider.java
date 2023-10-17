package com.cuckoofi.commonclientlibs.jwt;

import com.cuckoofi.commonclientlibs.config.JwtConfig;
import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.cuckoofi.commonclientlibs.constant.ResponseConstant;
import com.cuckoofi.commonclientlibs.entities.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtProvider {

    @Autowired
    private JwtConfig jwtConfig;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateJwtAuthToken(String username, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);

        // we can add some more information like below.
        claims.put(AuthConstant.JWT_CLAIMS_KEY_ROLES, roles);

        return Jwts.builder()
                .setClaims( claims )
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtConfig.getJwtExpiration() * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret())
                .compact();
    }

    public Boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(authToken);
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(authToken).getBody();
            if( claims.getExpiration().before(new Date()))
                throw new RuntimeException("Expired JWT token");
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: ", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: ", e);
        }
        return false;
    }

    public String getJwtTokenState(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(authToken);
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getJwtSecret()).parseClaimsJws(authToken).getBody();
            if( claims.getExpiration().before(new Date()))
                return ResponseConstant.TOKEN_EXPIRED;
            return ResponseConstant.TOKEN_VALID;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: ", e);
            return ResponseConstant.TOKEN_INVALID;
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: ", e);
            return ResponseConstant.TOKEN_EXPIRED;
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: ", e);
            return ResponseConstant.TOKEN_UNSUPPORTED;
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: ", e);
            return ResponseConstant.TOKEN_CLAIMS_EMPTY;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        return body.getSubject();
    }

    public Set<Role> getUserRoleFromJwtToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        // we can retrieve some more information like below.
        if( body.get(AuthConstant.JWT_CLAIMS_KEY_ROLES) == null ) {
            return null;
        }
        else {
            Set<Role> roleSet = new LinkedHashSet<>();
            List<Map<String, Object>> roles = (List<Map<String, Object>>)body.get(AuthConstant.JWT_CLAIMS_KEY_ROLES);
            for( Map<String, Object> roleObj : roles )
                roleSet.add(Role.of( Integer.parseInt(roleObj.get("id").toString()), roleObj.get("name").toString()));
            return roleSet;
        }
    }

    public List<GrantedAuthority> getAuthorities(String token){
        Claims body = Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();
        if( body.get(AuthConstant.JWT_CLAIMS_KEY_ROLES) == null )
            return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Map<String, Object>> roles = (List<Map<String, Object>>)body.get(AuthConstant.JWT_CLAIMS_KEY_ROLES);
        for( Map<String, Object> roleObj : roles )
            grantedAuthorities.add(new SimpleGrantedAuthority(roleObj.get("name").toString()));
        return grantedAuthorities;
    }

    public Integer getJwtExpiration(){
        return jwtConfig.getJwtExpiration();
    }
}