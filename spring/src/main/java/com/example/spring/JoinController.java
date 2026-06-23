package com.example.spring;


import com.example.spring.ex01.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ex01")
public class JoinController {

	@GetMapping("/join")
	public String join(){
		return "/ex01/join";
	}

//	@PostMapping("/join")
//	public String Post(
//			@RequestParam(required = false) String id,
//			@RequestParam(required = false) String pw,
//			@RequestParam(required = false) String name,
//			@RequestParam(required = false) int age) {
//		log.info("id: {}", id);
//		log.info("pw: {}", pw);
//		log.info("name: {}", name);
//		log.info("age: {}", age);
//
//		return "/ex01/join";
//	}

	@PostMapping("/join2")
	// Member type의 member변수
	public String Post(@ModelAttribute Member member) {
		log.info("id: {}", member.getUsername());
		log.info("pw: {}", member.getPassword());
		log.info("name: {}", member.getName());
		log.info("age: {}", member.getAge());
		log.info("member Info: {}", member);

		return "/ex01/join";
	}

	@ResponseBody // @ResponseBody : HTTP 데이터의 값을 Body에 그대로 담아서 전송
				  // @RequestBody : HTTP 요청 Body의 JSON을 객체로 변환
	@PostMapping("/api/join")
	public Member apiJoin(@RequestBody Member member) {
		log.info("member {}", member);
		return member;
	}


	// 경로 변수 (Path Variable)
	// /ex02/members -> 모든 회원 정보를 리턴
	@ResponseBody
	@GetMapping("/api/members")
	public List<Member> apiMembers(@RequestBody Member member) {
		log.info("members {}", member);
		return Collections.singletonList((member));
	}

	// /ex02/member?username=user1 -> 아이디가 user1인 회원 정보 리턴
	@ResponseBody
	@PostMapping("/api/member")
	public Member apiMember(@ModelAttribute Member member){
		log.info("member : {}", member);
		return member;
	}

	// /ex-2/api/members/user1 -> 변수를 경로처럼 사용해서 경로 변수라고 부른다
	// api url을 만들때 사용
	@ResponseBody
	@PostMapping("/api/member/{username}")
	public String apiMemberPath(@PathVariable String username){
		log.info("username: {}", username);
		return username;
	}
}
