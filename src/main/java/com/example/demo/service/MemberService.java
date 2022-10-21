package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.JpaMember;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// 자동 주입이 된다???
public class MemberService {

	private final MemberRepository memberRepository;

	// 추가
	public void save(JpaMember member) {
		memberRepository.save(member);
	}

	// 전체 조회
	public List<JpaMember> list() {
		return memberRepository.findAll();
	}
	
	// 전체 조회 페이징
	public Page<JpaMember> list(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}

	// 상세 조회
	// Optional : Null값을 처리하고 넘기기 (Null값이 있으면 오류가 뜸)
//	public Optional<JpaMember> detail(Long id) {
//		return memberRepository.findById(id);
//	}
	
	// 상세 조회
	// Null도 뜨게 하는 코드
	public JpaMember detail(Long id) {
		return memberRepository.findById(id).get();
	}
	
	//삭제
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	//수정(더티채킹)
	//@Transactional : 영속성 내용이 수정되면서 db에 넣어짐  / flush()라는 역할을 함?
	@Transactional
	public void update(JpaMember member) {
		JpaMember m = memberRepository.findById(member.getId()).get();
		m.setAddr(member.getAddr());
		m.setEmail(member.getEmail());
		m.setMemo(member.getMemo());
		m.setName(member.getName());
		m.setPassword(member.getPassword());	
	}
	
	
	
}
