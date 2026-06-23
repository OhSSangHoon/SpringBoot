package com.example.spring.ex01;

import lombok.*;

//
//@Getter //getter
//@Setter // setter
//@EqualsAndHashCode //equals
//@ToString //toString

//@NoArgsConstructor // 매개 변수를 받지 않는 기본 생성자
//@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자

@Data // getter, setter, equals, toString 전부를 만들어주는 어노테이션
public class Member {
	private String username; // 아이디
	private String password; // 패스워드
	private String name; // 이름
	private int age; // 나이

	// NoArgsConstructor
//	public Member() {
//
//	}

	// AllArgsConstructor
//	public Member(String username, String password, String name, int age) {
//		this.username = username;
//		this.password = password;
//		this.name = name;
//		this.age = age;
//	}

}
