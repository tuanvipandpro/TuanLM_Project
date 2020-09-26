package tuanlm.fpt.web.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
	private static final long EXPIRATION_TIME = 604800000L; //24h
	private static final String SECRET_KEY_APP = "TUANLM";
	private static final String HEADER_STRING = "Authorization";
	
	public static void addAuthentication(HttpServletResponse res, String username) {		
		res.addHeader(HEADER_STRING, getJwt(username));
	}
	
	public static String getJwt(String username) {
		Date now = new Date();
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY_APP)
				.compact();
	}
	
	public static String getUserFromToken(HttpServletRequest request) {
		String header = request.getHeader(HEADER_STRING);
		
		if (header != null) {
			return Jwts.parser()
					.setSigningKey(SECRET_KEY_APP)
					.parseClaimsJws(request.getHeader(HEADER_STRING))
					.getBody()
					.getSubject();
		}
		else {
			return null;
		}

	}
	
}
