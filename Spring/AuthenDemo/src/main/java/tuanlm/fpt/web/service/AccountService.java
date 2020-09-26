package tuanlm.fpt.web.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tuanlm.fpt.web.mapper.AccountMapper;
import tuanlm.fpt.web.model.Account;

@Service
public class AccountService implements UserDetailsService {
	private AccountMapper mapper;
	
	public AccountService(AccountMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Account account = Optional.of(mapper.getAccountByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException(username + " is not exist !"));
		return new User(
				account.getUsername(), 
				account.getPassword(), 
				Optional.of(mapper.getRoleList(username))
							.orElse(null)
							.stream()
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList()));
	}
	
}
