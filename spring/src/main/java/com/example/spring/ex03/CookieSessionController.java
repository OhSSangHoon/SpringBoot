package com.example.spring.ex03;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		Cookie cookie = new Cookie("sanghun99", userId);
		cookie.setMaxAge(60 * 30); // cookie 유효시간 설정
		cookie.setHttpOnly(true); // Js 접근 차단
		// cookie 전송 경로 지정(지정 경로의 하위 경로는 다 읽을 수 있음)
		// 상위 경로는 읽지 못함
		cookie.setPath("/");
		response.addCookie(cookie); // HttpServlet 응답 객체에 쿠키를 저장
		return "redirect:/";
	}
}
