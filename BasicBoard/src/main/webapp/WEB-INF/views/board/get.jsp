<%@ page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8"%>"
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class='page-header'>Board Read</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<div class="panel-body"></div>
			<div class="form-group">
				<label>Bno</label> <input class="form-control" name='bno'
					value='<c:out value="${board.bno}"/>' readonly="readonly">
			</div>
			<div class="form-group">
				<label>Title</label><input class="form-control" name='title'
					value='<c:out value="${board.title}"/>' readonly="readonly">
			</div>
			<div class="form-group">
				<label>Text area</label>
				<textarea class="form-control" rows="3" name='content'
					readonly="readonly">
			<c:out value="${board.content }" />
			</textarea>
			</div>
			<div class="form-group">
				<label>Writer</label><input class="form-control" name='writer'
					value='<c:out value="${board.writer }"/>' readonly="readonly">
			</div>
			
			<button data-oper='modify' class="btn btn-default"
			onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'">Modify</button>
			
			<button data-oper='list' class="btn btn-info" onclick="location.href='/board/list'">List</button>

			<!-- id= javascript에서 사용  : $('#operForm')-->			
			
			<form id='operForm' action="/board/modify" method="get">
			<!-- id= javascript에서 사용  : operForm.find("#bno").remove();-->
			<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
			<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
			<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
			<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
			<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
			</form>
			
		</div>


	</div>

</div>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	console.log("======== JS TEST========");
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	/* replyService.add(
		{reply:"JSTEST", replyer:"tester",bno: bnoValue},
		function(result){
			alert("RESULT: " +  result);
		}); */
	
	
	/* replyService.getList({bno: bnoValue, page: 1}, function(list){
		
		for(var i=0, len = list.length||0; i < len; i++){
			console.log(list[i]);
		} */
		
		/* replyService.remove(
		23, function(count){
			
			console.log(count);
			
			if(count === "success"){
				alert("REMOVED");
		}
			}, function(err){
				alert('ERROR....');
			
		
		
	}); */
	
		
		replyService.update({
			rno :22,
			bno : bnoValue,
			reply : "Modified Reply......"
		
		}, function(result){
			alert("수정 완료 ");
		
			
			
		});
		
</script>


<script type="text/javascript">
$(document).ready(function(){
	
	
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		
		operForm.attr("action","/board/modify").submit();
		
	});
	
	$("button[data-oper='list']").on("click", function(e){
		
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list")
		operForm.submit();
		
	});
	
	
	
})

</script>

<%@ include file="../includes/footer.jsp"%>
