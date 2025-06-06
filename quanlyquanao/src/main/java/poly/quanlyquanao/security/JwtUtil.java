package poly.quanlyquanao.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtil {
    // Khóa bí mật dùng để ký token, bạn nên đặt trong application.properties hoặc biến môi trường
//    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Thời gian sống token: 1 ngày (có thể thay đổi)
//    private final long jwtExpirationMs = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    // Lấy khóa ký từ chuỗi bí mật
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Tạo token mới từ username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // Thời điểm tạo token
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Thời gian hết hạn
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Trích xuất username từ token
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // Kiểm tra token đã hết hạn chưa
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Xác thực token có hợp lệ và đúng user không
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Lấy thông tin (claims) từ token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}