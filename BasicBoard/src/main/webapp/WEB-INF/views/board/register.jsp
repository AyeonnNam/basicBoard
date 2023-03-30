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

<script>

	$(document).ready(function(e){
		
		
		$(".uploadResult").on("click", "button", function(e){
			
			console.log("delete file");
			
		});
		
		
		var uploadUL = $(".uploadResult ul");
		
		function showUploadResult(uploadResultArr){
			
			if(!uploadResultArr || uploadResultArr.length == 0){ return; }
			
			
			var str = "";
			
			$(uploadResultArr).each(function(i, obj){
				
				if(obj.image){
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid
							
							+ "_" + obj.fileName);	
					
					str += "<li><div>";
					str += "<span> " + obj.fileName+ "</span>";
					str += "<button type= 'button' class= 'btn btn-warning btn-circle'><i class='fa fa-times'></li></i></button><br>";
					str += "<img src ='/display?fileName=" +fileCallPath+ "'>";
					str += "</div>";
					str += "</li>"; 
					
					
				}else{
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid
							
							+ "_" + obj.fileName);	
					
					str +="<li><div>";
					str +="<span> " +  obj.fileName + "</span>";
					str += "<button type= 'button' class= 'btn btn-default btn-circle'><i class='fa fa-times'></li></i></button><br>";
					str += "<img src ='/resources/img/png-transparent-heart-heart-thumbnail.png'></a>";
					str += "</div>";
					str += "</li>";
				}
			});
			
			uploadUL.append(str);
			
		}
		
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
				showUploadResult(result);
				
			}
			
		});//$.ajax
		
		
	});	
		
		
		
	});

</script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	
	


<%@include file="../includes/footer.jsp"%>