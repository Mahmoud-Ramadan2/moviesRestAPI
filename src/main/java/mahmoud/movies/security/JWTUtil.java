package mahmoud.movies.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String SECRET;


    @Value("${jwt.expiration}")
    private  long expirationTime;

    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities) {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                )
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .withIssuer("movies/350")
                .sign(Algorithm.HMAC256(SECRET));
    }


    public String validateTokenAndRetrieveEmail(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .withSubject("User Details")
                    .withIssuer("movies/350")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("email").asString();

    } catch (
    JWTVerificationException e){
        throw  new RuntimeException("Invalid or expired JWT token", e);
    }
    }
}
