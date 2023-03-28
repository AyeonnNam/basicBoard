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
		<input type='file' name='uploadFile' multiple="multiple">
	</div>


	<style>
.uploadResult {
	width: 100%;
	background-color: pink;
}
£
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
.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-items: center;
	top: 0%;
	width:100%;
	height:100%;
	background-color: pink;
	z-index: 100;
	background-color:rgba(255,255,255,0.5);	
}

.bigPicture{

	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img{
	width:600px;
}
</style>

	<div class='uploadResult'>
		<ul>

		</ul>
	</div>

	<div cass='bigPictureWrapper'>
		<div class='bigPicture'></div>
	</div>
	<button id='uploadBtn'>Upload</button>


	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>


	<script>
	
	function showImage(fileCallPath){
		
		alert(fileCallPath);
	}
	
	$(document).ready(function(){
		
		var uploadResult = $(".uploadResult ul");
		
			function showUploadedFile(uploadResultArr){
				
				var str= "";
				
				$(uploadResultArr).each(function(i, obj){
					
					if(!obj.image){
						
						var fileCallPath = encodeURIComponent(obj.uploadPath+ "/" +obj.uuid+ "_" + obj.fileName);

						str+= "<li><a href='/download?fileName="+fileCallPath+"'>"
								+"<img src='/resources/img/png-transparent-heart-heart-thumbnail.png'> " 
									+ obj.fileName + "</li>";
					}else{
						//str += "<li> " + obj.fileName + " </li>"; 
						
						var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_" +obj.uuid+ "_" + obj.fileName);
						
						var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
						
						originPath = originPath.replace(new RegExp(/\\/g),"/");
						
						
						str += "<li><a href=\"javascript:showImage(\'" + originPath +"\')\"><img src='/display?fileName="+fileCallPath+"'></li>";
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