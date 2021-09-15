package com.cos.prologstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.prologstart.domain.user.User;
import com.cos.prologstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	
	//1.패스워드는 알아서 체크 해쥰ㄴ다
	//2.리턴이 잘 되면 UserDetails 타입을 세션으로 만듬
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity==null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
	}
	
}
