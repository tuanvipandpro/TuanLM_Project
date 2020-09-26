package tuanlm.fpt.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tuanlm.fpt.web.request.LoginRequest;
import tuanlm.fpt.web.utils.JwtUtils;

@RestController
@RequestMapping("/login-service")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<String>(JwtUtils.getJwt(request.getUsername()), HttpStatus.OK);
	}
}
