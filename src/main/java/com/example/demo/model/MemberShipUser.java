package com.example.demo.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name ="membership_user")
public class MemberShipUser {
	
	@Id
	private String email;
	
	private String name;
	private Date createdDate;
	
	// @OneToOne :1대1 관계가 성립한다.라는 뜻
	// mappedBy : 참조
	
	@OneToOne(mappedBy = "owner") //즉시전략
	private MemberShipCard card;
}
