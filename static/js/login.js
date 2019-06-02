	    $("#login_btn").click(function () {
			alert($("#phone_number").val());
			alert($("#password").val());
        $.ajax({
        type: "POST",
        url: "/identity/all/login",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "phone_number": $("#phone_number").val(),
            "password": $("#password").val()
        }),
        dataType: "json",
        success: function (message) {
			alert(message.state);
            if (message.state != -1 ) {
				if(message.state == 0){
					alert("借入者登录成功！");
					location.href = "/static/borrower/index_borrower.html";
				}else{
					alert("借出者登录成功！");
					location.href = "/static/lender/index_lender.html";
				}
            }
			else{
				alert("登录失败，请重新登录~");
				location.href = "/static/login.html";
			}
        },
        error: function () {
            alert("登录失败，请重新登录~");
			location.href = "/static/login.html";
        }
		})
    });