package pl.platrykp.cubeformservice.components;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.platrykp.cubeformservice.details.UserDetailsImpl;
import pl.platrykp.cubeformservice.util.Role;

import java.util.Date;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt_secret}")
    private String SECRET;

    public String generateToken(UserDetailsImpl user) {
        return doGenerateToken(user.getUsername(), user.getRole().getId());
    }

    private String doGenerateToken(String subject, int role) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * @param token token
     * @return null if token is incorrect
     */
    public @Nullable TokenData parseToken(String token) {
        try {
            return new TokenData(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody());
        }catch (  ExpiredJwtException
                | UnsupportedJwtException
                | MalformedJwtException
                | SignatureException
                | IllegalArgumentException er) {
            return null;
        }
    }

    public static final class TokenData {
        private final Claims claims;

        public TokenData(Claims claims){
            this.claims = claims;
        }

        public String getUsername() {
            return claims.getSubject();
        }

        public Date getExpirationDate() {
            return claims.getExpiration();
        }

        public Role getRole() {
            return Role.fromId(claims.get("role", Integer.class));
        }

        public Boolean isExpired() {
            final Date expiration = getExpirationDate();
            return expiration.before(new Date());
        }

    }
}
