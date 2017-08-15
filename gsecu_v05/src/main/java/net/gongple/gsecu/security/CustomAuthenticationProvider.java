package net.gongple.gsecu.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.gongple.gsecu.domain.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired CustomUserDetailsService userDetailsSvc;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = null;
		Collection<? extends GrantedAuthority> auths = null;
		try {
			user = userDetailsSvc.loadUserByUsername(username);
			if(user == null) throw new UsernameNotFoundException("사용자 아이디를 찾을 수 없습니다.");
			
			System.out.println("@@@ input password : "+ password +" , password : "+ user.getPassword());
			
			if(!password.equals(user.getPassword())) {
				throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
			} else {
				auths = user.getAuthorities();
			}
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return new UsernamePasswordAuthenticationToken(user, password, auths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
