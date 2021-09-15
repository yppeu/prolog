package com.cos.prologstart.config.oauth;




import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.prologstart.config.auth.PrincipalDetails;
import com.cos.prologstart.domain.user.User;
import com.cos.prologstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor //로그인하기 위해 필요 
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//System.out.println("OAuth2 서비스 탐");
		OAuth2User oauth2User = super.loadUser(userRequest);
		//System.out.println(oauth2User.getAttributes());		//Facebook에서 정보 받아오기 
		
		Map<String, Object> userInfo = oauth2User.getAttributes();
		
		
		String username = "facebook_"+(String)userInfo.get("id");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String email = (String)userInfo.get("email");
		String name = (String)userInfo.get("name");
		
		
User userEntity=userRepository.findByUsername(username);
		
		if(userEntity== null) { 	//최초ㄱ로그인
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.role("ROLE_USER")
					.build();
			
			return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
			
		}else {		//페이스북으로 이미 회원가입되ㅓ어있음
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
		
		
		

		
		
	}
}
