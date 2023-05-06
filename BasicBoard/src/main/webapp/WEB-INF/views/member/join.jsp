<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">MEMBER JOIN</h1>
	</div>
</div>

	 <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">BE OUR MEMBER</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method='post' action="/member/join">
                        					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        
                            <fieldset>
                                <div class="form-group"><label>ID</label>
                                 <input class="form-control" id= "id_input" name="userid"> 
                                    <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
                                   <span class="id_input_re_2">이미 이용 중인 아이디입니다.</span>
                               
                                   </div>
                                <div class="form-group"><label>PW</label>
                                    <input class="form-control" name="userpw">
                                </div>
                                <div class="form-group"><label>닉넴</label>
                                    <input class="form-control" name="userName">
                                </div>
                                
                                <button type="submit" class="btn btn-success">JOIN</button>  
                                <button type="submit" class="btn btn-warning">LOGIN</button>
                                 
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<style>
.id_input_re_1{
		color : green;
		display : none;
	}
	/* 중복아이디 존재하는 경우 */
	.id_input_re_2{
		color : red;
		display : none;
	}
</style>
	<script>
		$(document).ready(function(){
			
			$(".btn-success").on("click", function(e){
				alert("회원가입 성공!  서비스 이용시 로그인 해주세요");
				e.preventDefault();
				$("form").submit();
			});
			
			//아이디 중복 검사 
			$('#id_input').on("input", function(){
				
				//console.log("keyUp 테스트");
				var userid = $('#id_input').val();
				var data = {userid:userid};
				
				$.ajax({
					type : 'POST',
					//url 수정 후 ajax 동작 
				 	url : '${pageContext.request.contextPath}/member/memberIdChk',
				 	data: data,
				 	beforeSend : function(
							xhr) {
						//변수처리 안하고 직접 넣음 
						 xhr
								.setRequestHeader(
										"${_csrf.headerName}",
										"${_csrf.token}"); 
						//xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);

					},
				 	success : function(result) {
				 		console.log( "성공여부 : " + result);
				 	}//end success
					
					
					
				});//end ajax
				
			});// end idcheck 
			
		});
	
	
	</script>
<%@include file="../includes/footer.jsp"%>
