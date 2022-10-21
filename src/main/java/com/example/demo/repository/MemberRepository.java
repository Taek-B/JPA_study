package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.JpaMember;

// T : 대응되어야할 Entity??
// Long : 기본키(@Id)의 type? 
public interface MemberRepository extends JpaRepository<JpaMember, Long>{
	
}
