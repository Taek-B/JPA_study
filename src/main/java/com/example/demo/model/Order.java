package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tbl_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderid;
	
	private String note;
	private String ordername;
	private int price;
	
	// LAZY : 지연절약?
	// EAGER : 즉시절약 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") //외래키 설정
	private User user; 
	
}
