package com.example.spring.ex02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/ex02")
@Controller
public class BasicController {

	@GetMapping("")
	public String index() {
		return "ex02/index";
	}


	// 내가 한 방식
	@GetMapping("/{text}")
	public String text(@PathVariable String text, Model model) {
		log.info("작성된 텍스트: {}", text);
		model.addAttribute("text", text);
		return "ex02/text";
	}


	@GetMapping("/text")
	public String text(Model model){

		model.addAttribute("data", "<b>hello world</b>");

		return "ex02/text";
	}

	@GetMapping("/variable")
	public String variable(Model model){
//		log.info("{}",model);
		User userA = new User("sanghun", 28);
		User userB = new User("sanghuni", 22);

//		model.addAttribute("key", value) 형식으로 데이터를 저장

		model.addAttribute("user", userA);
		model.addAttribute("users", List.of(userA, userB));
		model.addAttribute("userMap", Map.of("userA", userA, "userB", userB));
		model.addAttribute("now", LocalDateTime.now());
		return "ex02/variable";
	}


	// URL 링크 query parameter
	@GetMapping("/link")
	public String link(Model model){
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "ex02/link";
	}

	@GetMapping("/each")
	public String each(Model model){
		List<User> list = List.of(
				new User("userA", 20),
				new User("userB", 25),
				new User("userC", 23));
		model.addAttribute("users", list);
		return "ex02/each";
	}

}
