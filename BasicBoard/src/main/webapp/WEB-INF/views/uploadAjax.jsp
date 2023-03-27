<%@ page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>upload With Ajax</h1>
	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>
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
	<div class='uploadResult'>
		<ul>

		</ul>
	</div>

	<button id='uploadBtn'>Upload</button>


	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>


	<script>
	$(document).ready(function(){
		
		var uploadResult = $(".uploadResult ul");
		
			function showUploadedFile(uploadResultArr){
				
				var str= "";
				
				$(uploadResultArr).each(function(i, obj){
					
					if(!obj.image){
						
						str+= "<li><img src='/resources/img/png-transparent-heart-heart-thumbnail.png'> " 
									+ obj.fileName + "</li>";
					}else{
						str += "<li> " + obj.fileName + " </li>"; 
					}
					
				});
				
				uploadResult.append(str);
				
			}	
		
		
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5mb
		
			function checkExtension(fileName, fileSize){
			
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;
				
			}
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드할 수 없슴.");
			}
			return true;
			
		}
		
		
		
			/* <input type='file'>은 readOnly라 안쪽내용 수정 불가능, 
			별도의 방법으로 초기화, 아무내용 없는 객체<div>를 복사 ,
			업로드 후에 추가해서 초기화하기 
			*/ 
			var cloneObj = $(".uploadDiv").clone();
		
		
			
		$("#uploadBtn").on("click", function(e){
			
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for(var i =0;i<files.length;i++){
				
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}
				
				
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '${pageContext.request.contextPath}/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType: 'json',
				success : function(result){
					console.log(result);
					alert("Uploaded");
					
					showUploadedFile(result);
					
					$(".uploadDiv").html(cloneObj.html());
				}
				
				
			}); //$.ajax
			
		});	
	});
</script>


</body>



</html>