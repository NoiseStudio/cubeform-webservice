package pl.platrykp.cubeformservice.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.platrykp.cubeformservice.details.AuthUserDetails;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {


    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt_secret}")
    private String SECRET;


    public String generateToken(AuthUserDetails user) {
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

    public static final class TokenData {
        private final Claims claims;

        public TokenData(Claims claims){
            this.claims = claims;
        }

        public String getUsernameFromToken() {
            return getClaimFromToken(Claims::getSubject);
        }

        public Date getExpirationDateFromToken() {
            return getClaimFromToken(Claims::getExpiration);
        }

        private Boolean isTokenExpired() {
            final Date expiration = getExpirationDateFromToken();
            return expiration.before(new Date());
        }


        public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
            return claimsResolver.apply(claims);
        }

        public Boolean validateToken(UserDetails userDetails) {
            final String username = getUsernameFromToken();
            return (
                    username.equals(userDetails.getUsername())
                            && !isTokenExpired());
        }

    }
}
