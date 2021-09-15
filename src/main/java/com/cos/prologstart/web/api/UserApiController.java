package com.cos.prologstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.prologstart.config.auth.PrincipalDetails;
import com.cos.prologstart.domain.user.User;
import com.cos.prologstart.handler.ex.CustomValidationApiException;
import com.cos.prologstart.service.SubscribeService;
import com.cos.prologstart.service.UserService;
import com.cos.prologstart.web.dto.CMRespDto;
import com.cos.prologstart.web.dto.subscribe.SubscribeDto;
import com.cos.prologstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	private final SubscribeService subscribeService;
	
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, 
			@AuthenticationPrincipal PrincipalDetails principalDetails){
			
		User userEntity = userService.회원프로필사진변경(principalId, profileImageFile);
		principalDetails.setUser(userEntity);
		//회원프로필사진이 변경되고 나면 세션값이 바껴야 하니까 변경된 유저엔티티를 받아서 principalDetails.setUser 에 넣어준다.
		return new ResponseEntity<>(new CMRespDto<>(1, "성공", null), HttpStatus.OK);
	}
	
	
	@GetMapping("/api/user/{pageUserId}/subscribe")	//페이지유저가 구독하고 있는 모든 정보를 가지고 온다.
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDto> subscribeDto = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 불러오기 성공", subscribeDto), HttpStatus.OK);
	}
	
	
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto, 
			BindingResult bindingResult,	// @Valid 가 적혀있는 다음 파라메터에 적어야함 
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
	
		if(bindingResult.hasErrors()) {
			Map<String,String>errorMap=new HashMap<>();
			
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());				
			}
			throw new CustomValidationApiException("유효성검사 실패", errorMap);
		}else {
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity);	//세션 정보 ㅂ변경
			return new CMRespDto<>(1, "회원수정완료", userEntity);	//응답시에 userEntityㅇ릐 모든 getter 함수가 호출되거 JSON 으로 파싱하여 응답
		}
	}
}
