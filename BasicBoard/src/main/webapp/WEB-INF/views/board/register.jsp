<%@ page language="java" contentType="text/html; cahrset=UTF-8"
	pageEncoding="UTF-8"%>"
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http //www.w3.org/TR/html4/loose.dtd">
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

				<form role="form" action="/board/register" method="post">
				
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
				</form>
				
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
					<input type="file"  name="uploadFile" multiple>
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

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	

<script>

	$(document).ready(function(e){
		
		var formObj = $("form[role='form']");
		//등록 버튼 누룰 때 
		$("button[type='submit']").on("click", function(e){
			
			//첨부파일 관련된 처리를 할 수 있도록 기본 동작 막기 
			e.preventDefault();
			console.log("submit clicked");
			var str ="";
		
			$(".uploadResult ul li").each(function(i, obj){
				
				var jobj = $(obj);
				console.dir(jobj);
				
				str +="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uuid' value ='" +jobj.data("uuid") +"'>";
				str += "<input type='hidden' name='attachList["+i+"].uploadPath'  value='" + jobj.data("path")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].fileType'  value= '" + jobj.data("type")+"'>";
			});
			
			formObj.append(str).submit();
				
		});
		
		
		
		
		var uploadUL = $(".uploadResult ul");
		
		function showUploadResult(uploadResultArr){
			
			if(!uploadResultArr || uploadResultArr.length == 0){ return; }
			
			
			var str = "";
			
			$(uploadResultArr).each(function(i, obj){
				
				if(obj.image){
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid
							
							+ "_" + obj.fileName);	
					
					str += "<li data-path='"+obj.uploadPath+"'";
					str += "data-uuid='" + obj.uuid +"' data-fileName='"+ obj.fileName +"'data-type='" + obj.image+"'"
					str += "><div>";
					str += "<span> " + obj.fileName+ "</span>";
					str += "<button type= 'button' data-file=\'"+fileCallPath+"\' data-type='image' class= 'btn btn-default btn-circle btn-xs'><i class='fa fa-times' style='font-size:20px'></li></i></button><br>";
					str += "<img src ='/display?fileName=" +fileCallPath+ "'>";
					str += "</div>";
					str += "</li>"; 
					
					
				}else{
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid
							
							+ "_" + obj.fileName);	
					
					str +="<li " 
					str += "data-path='" + obj.uploadPath+ "' data-uuid=' "+obj.uuid +"' data-fileName=' "
							+obj.fileName+ "' data-type='" +obj.image+"' ><div>";
					str +="<span> " +  obj.fileName + "</span>";
					str += "<button type= 'button' data-file=\'"
								+ fileCallPath +
								"\'data-type='file' class= 'btn btn-default btn-circle btn-xs'><i class='fa fa-times' style='font-size:20px'></li></i></button><br>";
					str += "<img src ='/resources/img/docu.jpeg'></a>";
					str += "</div>";
					str += "</li>";
				}
			});
			
			uploadUL.append(str);
			
		}
		
		
		
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
			
			url: '${pageContext.request.contextPath}/uploadAjaxAction',
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
		
	
	// 삭제 버튼 
	$(".uploadResult").on("click", "button", function(e){
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		var targetLi = $(this).closest("li");
		
		$.ajax({
			
			url: '/deleteFile',
			data: {fileName: targetFile, type:type},
			dataType: 'text',
			type: 'POST',
			success: function(result){
				alert(result);
				targetLi.remove();
			}
			
			
		}); //$.ajax
	
		
	});
	
		
		
	});

</script>

	


<%@include file="../includes/footer.jsp"%>