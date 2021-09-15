package com.cos.prologstart.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.prologstart.config.auth.PrincipalDetails;
import com.cos.prologstart.domain.board.Board;
import com.cos.prologstart.handler.ex.CustomValidationException;
import com.cos.prologstart.service.BoardService;
import com.cos.prologstart.web.dto.board.BoardUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		//DI
@Controller
public class BoardController {
	
	
	
	private final BoardService boardService;
	
	@GetMapping("/board/story")
	public String story() {
		return "board/story";
	}

	
	//API 구현한다면 - 이유 - (브라우저에서 요청하는게 아니라, 안드로이드 나 iOS에서 요청할때) 
	@GetMapping("/board/popular")
	public String popular(Model model) {
		
		//api 는 데이터를 리턴하는 서버, 그러므로 컨트롤러에서 실행 해준ㄷ다	//모델에 담고 데이터 들고가기만 하면 된다
		List<Board> boards = boardService.인기페이지();
		
		model.addAttribute("boards", boards); 
		
		return "board/popular";
	}
	
//	@GetMapping("/board/boardMain")
//	public String boardMain(Model model) {
//		
//		//api 는 데이터를 리턴하는 서버, 그러므로 컨트롤러에서 실행 해준ㄷ다	//모델에 담고 데이터 들고가기만 하면 된다
//		List<Board> boards = boardService.전체게시판();
//		
//		model.addAttribute("boards", boards); 
//		
//		return "board/popular";
//	}
	
	
	
	
	@GetMapping("/board/upload")
	public String upload() {
		return "board/upload";
	}
	
	/*
	 * @PostMapping("/board") //로그인되어있는 유저정보 받기 public String
	 * boardUpload(BoardUploadDto boardUploadDto, @AuthenticationPrincipal
	 * PrincipalDetails principalDetails) {
	 * 
	 * if(boardUploadDto.getFile().isEmpty()) {
	 * 
	 * throw new CustomValidationException("이미지가 첨부되지 않았습니다.",null); }
	 * 
	 * 
	 * 
	 * boardService.게시글업로드(boardUploadDto, principalDetails); return
	 * "redirect:/user/"+principalDetails.getUser().getId(); //현재 로그인한 유저의 id }
	 */
}
