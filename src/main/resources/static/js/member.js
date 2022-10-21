$("#btnUpdate").click(function(){
	var dataParam={
		// form 안에 글자를 가져올꺼면 val()를 사용
		"id": $("#id").val(),
		"name": $("#name").val(),
		"password": $("#password").val(),
		"email": $("#email").val(),
		"addr": $("#addr").val(),
		"memo": $("#memo").val(),		
	}
	// alert('수정')
	$.ajax({
		type:'PUT',
		url:"/update/",
		data:JSON.stringify(dataParam),
		contentType:"application/json;charset=utf-8" 
	})
	.done(function(resp){
		alert(resp +" : 수정성공")
		location.href="/list"
	})
	.fail(function(e){
		alert(e +" : 수정실패")
	})
})