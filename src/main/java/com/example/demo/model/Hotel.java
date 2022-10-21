package com.example.demo.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	// @Enumerated : Enum 파일의 타입을 지정할 수 있음??
	@Enumerated(EnumType.STRING)
	private Grade grade;
	
	//@Embedded : @Embeddable 로 지정해준 class를 받아올 수 있음?
	@Embedded
	private Address address;
	
	//Review에 있는 'hotel'를 받아옴
	@OneToMany(mappedBy = "hotel")
	//리뷰가 여러개이므로 List형식으로 받음
	private List<Review> reviews;
}
