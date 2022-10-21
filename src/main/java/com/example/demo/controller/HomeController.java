package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.JpaMember;
import com.example.demo.service.MemberService;


@Controller
public class HomeController {

	@Autowired
	private MemberService memberservice;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/join")
	public String join(JpaMember member) {
		memberservice.save(member);
		return "redirect:list";
	}

	// 전체보기 (페이징이 없음)
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<JpaMember> members = memberservice.list();
//		model.addAttribute("lists", members);
//		return "list";
//	}
	
	// 전체보기 (페이징)
	// lists = 데이터 + page데이터를 포함
	// lists.content를 써야 view화면에서 순수 데이터만 받음
	// direction = Sort.Direction.DESC : 내림차순
	// @PageableDefault() : 페이지 관련 설정
	@GetMapping("/list")
	public String list(Model model,
			@PageableDefault(size = 3,sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		System.out.println("pageable:" + pageable);
		Page<JpaMember> list2 = memberservice.list(pageable);
		model.addAttribute("lists", list2);
		return "list";
	}

	// 상세보기
	// @PathVariable : 주소에 있는 값인 {id}를 받을 수 있음???
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberservice.detail(id)); 
		return "detail";
	}
	
	// 상세보기2
	// @ResponseBody : 객체를 들고 view쪽으로 정보를 가져간다?
	@GetMapping("/detail2/{id}")
	@ResponseBody
	public JpaMember detail2(@PathVariable Long id) {
		return memberservice.detail(id);
	}
		
	
	
	//삭제 : 뷰로 가는게 아닌 데이터를 주는 컨트롤러
	//void : Mapping이랑 넘어가는 주소가 같으면 void 사용
	//데이터값을 넘어가는 것을 알려주기 위해 ResponseBody라는 어노테이션을 씀
	//컨트롤러 전체를 데이터를 주는 컨트롤러로 생각해서 만든다면 RestController 어노테이션 이용
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Long delete(@PathVariable Long id) {
		memberservice.delete(id);
		return id;
	}
	
	//수정폼으로
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberservice.detail(id)); 
		return "update";
	}
	
	//수정하기
	// @RequestBody : json형태의 데이터를 받아옴
	@PutMapping("/update")
	@ResponseBody
	public String update(@RequestBody JpaMember member) {
		System.out.println("addr:" + member.getAddr());
		System.out.println("email:" + member.getEmail());
		System.out.println("id:" + member.getId());
		
		memberservice.update(member);
		
		return "success";
	}
	
}
