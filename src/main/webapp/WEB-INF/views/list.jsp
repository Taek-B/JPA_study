<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
	<h3>LIST(<a href="/">Home</a>)</h3>
	<div id="memberDiv">
		<c:forEach items="${lists.content}" var="member">
			아이디 : ${member.id} <br />
			이름 : ${member.name} <br />
			주소 : ${member.addr} <br />
			이메일 : ${member.email} <br />
			<a href="/detail/${member.id}" data-detailNum="${member.id}">상세보기</a> <br/>
			<a href="javascript:funDetail(${member.id})">상세정보 바로보기(ajax)</a> <br/>
			<button type="button" onclick="funDelete(${member.id})">삭제</button>
			<button type="button" class="btnDel" data-mid = "${member.id}">삭제class</button>
			<br />
			<br />
		</c:forEach>
	</div>
	<a href="?page=${lists.number-1}">이전</a>
	<a href="?page=${lists.number+1}">다음</a>
	<br/>
	<br/>
	<div id="detailDiv"></div>
	<script>
	// 삭제 함수
	var delfun = function(){
		//alert로 먼저 실행되는지 확인하는게 좋음
		//alert($(this).attr('data-mid'))
		//alert($(this).data('mid'))
		$.ajax({
			type:'DELETE',
			url:'/delete/'+$(this).data('mid')
		})
		.done(function(resp){
			alert(resp+'번 삭제 성공')
			location.href='/list'
		})
		.fail(function(e){
			alert(e+'번 삭제 실패')
		})
	}
	/////////////////////////////////////////////////
	// 상세정보 함수
	function funDetail(id){
		$.ajax({
			type:'get',
			url:'/detail2/'+id
		})
		.done(function(resp){
			// alert('성공:' + resp.name)
			str = "name : " + resp.name + "<br/>"
			str += "email : " +resp.email+"<br/>"
			str += "memo : " +resp.memo+"<br/>"
			$("#detailDiv").html(str);
		})
		.fail(function(e){
			alert('실패')
		})
		
	}
	
	////////////////////////////////////////////////////
	// $("어디영역에서?").on("동작?","class명?",함수)
	$("#memberDiv").on("click",".btnDel",delfun)
	
	
	// 함수를 같이 넣어주면 밑에 코드처럼 나옴
	/*
	$("#memberDiv").on("click",".btnDel",function(){
		//alert로 먼저 실행되는지 확인하는게 좋음
		//alert($(this).attr('data-mid'))
		//alert($(this).data('mid'))
		$.ajax({
			type:'DELETE',
			url:'/delete/'+$(this).data('mid')
		})
		.done(function(resp){
			alert(resp+'번 삭제 성공')
			location.href='/list'
		})
		.fail(function(e){
			alert(e+'번 삭제 실패')
		})
	})
	*/
	</script>
	
	<script>
	function funDelete(id){
	// alert(id)
		$.ajax({
			type:'DELETE',
			url:'/delete/'+id,
		})
		.done(function(resp){
			alert(resp+'번 삭제 성공')
			location.href='/list'
		})
		.fail(function(e){
			alert(e+'번 삭제 실패')
		})
	}
</script>
</body>
</html>