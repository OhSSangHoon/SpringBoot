package com.example.spring.ex01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

/*
	[ Forward (서버 내부 이동) ]
	  - return forward:/ 경로
	  - 서버가 내부적으로 다른 핸들러로 요청을 넘김
	  - 브라우저 URL 반경이 없음 (요청한 URL을 그대로 유지)
	  - 하나의 Request 객체가 유지됨

	[ Model ]
	  - 컨트롤러에서 뷰로 데이터를 전달하는 객체
	  - 메서드의 파라미터로 Model을 선언하면 Spring에서 자동으로 생성하여 주입
	  - model.addAttribute("key", value) 형식으로 데이터를 저장하면
		내부적으로 HttpServletRequest의 attribute에 저장 (Request Scope)
	  - 뷰(타임리프)에서 ${Key} 형식으로 값을 꺼낼 수 있음, 다른 핸들러에서는
	  	＠RequestAttribute 어노테이션을 사용해서 꺼낼 수 있다.

	[ HttpServletRequest ]
  	요청 데이터가 언제까지 유지되냐에 따라서 4가지 scope이 존재
 	  - 1. Page scope
      - 2. Request scope
 	  - 3. Session scope
 	  - 4. Application scope


 상품 주문
  1. 주문 처리 후 주문 결과 페이지로 이동
  2. 주문 처리 후 상품 페이지로 이동

 * */
@Slf4j
@Controller
@RequestMapping("/ex01")
public class ForwardRedirectController {
	@GetMapping("/products")
	public String products() {
		return "/ex01/products";
	}

	// 주문 처리
	@GetMapping("/orders/{productid}")
	public String orders(@PathVariable String productid, Model model) {
//		log.info("선택한 상품 ID: {}", productid);
		model.addAttribute("productid", productid);
		return "forward:/ex01/orders/result";
	}

	// 주문 결과
	@GetMapping("/orders/result")
	public String result(@RequestAttribute String productid) {

		log.info("주문 완료: {}", productid);
		return "/ex01/result";
	}

	/*
		[ Redirect (클라이언트 재요청) ] -> return "redirect:/경로"
		  - 서버가 응답을 보내면 브라우저가 세 URL로 재요청
		  - 브라우저 URL이 새 URL로 변경됨
		  - 새로운 Request 객체가 생성됨 -> Model에 담아도 전달 안됨

		  [　Forward는 언제 사용?　]
		   - 같은 요청 안에서 처리 결과를 다른 핸들러나 뷰에 넘길 때
		   - URL을 변경하지 않아도 되는 경우(사용자에게 내부 경로를 숨길 때) 사용
		   - Model에 담은 데이터를 그래도 전달해야할 때

		  [ Redirect는 언제 사용? ]
		   - 요청 처리 후 결과 페이지로 이동할 때(새로고침 중복 방지 역활)
	*/

	// Redirect　반환
	@GetMapping("/orders/redirect/{productid}")
	public String orderRedirect(@PathVariable String productid) {
		log.info("주문 상품 아이디: {}", productid);
		return "redirect:/ex01/orders/{productid}";
	}

	// 주문 결과
	@GetMapping("/orders/result2")
	public String resultRedirect(@RequestAttribute String productid) {

		log.info("주문 완료: {}", productid);
		return "/ex01/orders/result2";
	}

}
