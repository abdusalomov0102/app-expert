package uz.lesson.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private String boltaKey = "blablakeykeraklikey";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS512, boltaKey)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(boltaKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
//        catch (SignatureException ex) {
//            System.out.println("Invalid JWT signature");
//        } catch (MalformedJwtException exception) {
//            System.out.println("Invalid JWT token");
//        } catch (ExpiredJwtException exception) {
//            System.out.println("Expired JWT token");
//        } catch (UnsupportedJwtException exception) {
//            System.out.println("Unsupported JWT token");
//        } catch (IllegalArgumentException exception) {
//            System.out.println("JWT claims string is empty.");
//        }
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(boltaKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
