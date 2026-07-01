package com.example.spring.ex03;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/ex03")
@Controller
public class CookieSessionController {
	@GetMapping("")
	public String index(){
		return "ex03/index";
	}

	// 쿠키 생성 -> 응답에 Set Cookie로 데이터를 보낸다.
	@GetMapping("/cookie/set")
	public String setCookie(@RequestParam String userId,
							HttpServletResponse response) {
		log.info("userId = {}", userId);

		Cookie cookie = new Cookie("userId", userId);
		cookie.setMaxAge(60 * 30); // cookie 유효시간 설정
		cookie.setHttpOnly(true); // Js 접근 차단
		// cookie 전송 경로 지정(지정 경로의 하위 경로는 다 읽을 수 있음)
		// 상위 경로는 읽지 못함
		cookie.setPath("/");
		response.addCookie(cookie); // HttpServlet 응답 객체에 쿠키를 저장
		return "redirect:/";
	}

	// 쿠키 읽기
	@ResponseBody
	@GetMapping("/cookie/get")
	public String getCookie(@CookieValue(name = "userId", required = false) String userId) {
		return "쿠키에서 읽은 UserId = " + userId;
	}

	// 쿠키 삭제
	@GetMapping("/cookie/remove")
	public String removeCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("userId", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/";
	}

	// session 생성
	@GetMapping("/session/set")
	public String setSession(@RequestParam String userId, HttpServletRequest request){
//		log.info("userid = {}", userId);

		// session 정보 읽어오기
		HttpSession session = request.getSession(); // 1. 기존 session이 있으면 반환, 없으면 생성
		// 2. session data를 {key, value} 형식으로 저장
		session.setAttribute("userId", userId);
		return "redirect:/";
	}

	// session 조회
	@ResponseBody
//	@GetMapping("/session/get")
	public String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		return "세션에서 읽은 UserId = " + userId;
	}

	// session 조회
	@ResponseBody
	@GetMapping("/session/get")
	public String getSession2(@SessionAttribute String userId) {
		return "세션에서 읽은 UserId = " + userId;
	}

	// session 삭제
	@GetMapping("/session/remove")
	public String removeSession(HttpSession session) {
		session.removeAttribute("userId");
		log.info("success session remove!!");
		return "redirect:/";
	}

//	// session 삭제
//	@GetMapping("/session/remove")
//	public String removeSession(HttpServletRequest request) {
//		// false를 주면 세션이 없을 때 새로 생성하지 않고 null을 리턴
//		HttpSession session = request.getSession(false);
//		if(session != null){
//			// session 데이터를 모두 삭제
//			log.info("success session remove!!");
//			session.invalidate();
//		}
//		return "redirect:/";
//	}
}
