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
                        <h3 class="panel-title">회원 가입</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method='post' action="/member/join">
                        					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        
                            <fieldset>
                                <div class="form-group"><label>ID</label>
                                    <input class="form-control" name="userid">
                                </div>
                                <div class="form-group"><label>PW</label>
                                    <input class="form-control" name="userpw">
                                </div>
                                <div class="form-group"><label>닉넴</label>
                                    <input class="form-control" name="userName">
                                </div>
                                
                                <button type="submit" class="btn btn-default">가입완료</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>



	<script>
	$(".btn-success").on("click", function(e){
		
		e.preventDefault();
		$("form").submit();
	});
	
	</script>
<%@include file="../includes/footer.jsp"%>
