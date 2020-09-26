package tuanlm.fpt.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import tuanlm.fpt.web.service.AccountService;
import tuanlm.fpt.web.utils.JwtUtils;

public class AuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private AccountService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication auth = null;
		String username = JwtUtils.getUserFromToken(request);
		
		if (username != null) {
			auth = new UsernamePasswordAuthenticationToken(username, null, service.loadUserByUsername(username).getAuthorities());
		}
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}

}
