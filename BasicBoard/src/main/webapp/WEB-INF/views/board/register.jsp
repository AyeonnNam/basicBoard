<%@ page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8"%>"
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Register</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Register</div>
			<div class="panel-body">

				<form role="form" action="/board/register" method="post"></form>
				<div class="form-group">
					<label>Title</label> <input class="form-control" name='title'>
				</div>

				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name='content'></textarea>
				</div>

				<div class="form-group">
					<label>Writer</label> <input class="form-control" name='writer'>
				</div>

				<button type="submit" class="btn btn-default">Submit Button</button>

				<button type="reset" class="btn btn-default">Reset Button</button>




			</div>
		</div>
	</div>
</div>



<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">File Attach</div>
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" multiple>
				</div>

				<div class='uploadResult'>
					<ul>

					</ul>
				</div>

			</div>

		</div>


	</div>

</div>

<script>
	$(document).ready(function(e){
		
		var formObj = $("form[role='form']");
		
		$("button[type='submit']").on("click", function(e){
			
			e.preventDefault();
			
			console.log("submit clicked");
			
			
		});
		
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;
		
		function checkExtension(fileName, fileSize){
			
			if(fileSize >=maxSize){
				
				alert(" 파일 사이즈 초과 ");
				return false;
			}
			
			if(regex.test(fileName)){
				
				alert("해당 종류의 파일은 업로드할 수 없다.")
				return false;
			}
			return true;
			
		}
		
	$("input[type='file']").change(function(e){
		
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		for(var i=0; i<files.length;i++){
			if(!checkExtension(files[i].name, files[i].size)){
				
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data:formData,
			type:'POST',
			dataType:'json',
			success: function(result){
				console.log(result);
				
				
			}
			
		});//$.ajax
		
		
	});	
		
		
		
	});

</script>

<style>
.uploadResult {
	width: 100%;
	background-color: pink;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 20px;
}

</style>

<%@include file="../includes/footer.jsp"%>