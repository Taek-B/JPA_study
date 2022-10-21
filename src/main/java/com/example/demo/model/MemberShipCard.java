package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name ="membership_card")
public class MemberShipCard {
	
	@Id
	@Column(name="card_number")
	private String cardNumber;
	
	private Date expiryDate;
	private boolean enabled;
	
	//@OneToOne : 1대1 관계가 성립한다.라는 뜻
	// @JoinColumn : 다른 테이블 Join하는 코드
	@OneToOne
	@JoinColumn(name="user_email") // 외래키 설정
	private MemberShipUser owner;
	
	//지연절약? : 필요할때만 조인할 수 있음
}
