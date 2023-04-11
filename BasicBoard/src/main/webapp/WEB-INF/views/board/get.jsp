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

			<button data-oper='list' class="btn btn-info"
				onclick="location.href='/board/list'">List</button>

			<!-- id= javascript에서 사용  : $('#operForm')-->

			<form id='operForm' action="/board/modify" method="get">
				<!-- id= javascript에서 사용  : operForm.find("#bno").remove();-->
				<input type='hidden' id='bno' name='bno'
					value='<c:out value="${board.bno}"/>'> <input type='hidden'
					name='pageNum' value='<c:out value="${cri.pageNum }"/>'> <input
					type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
				<input type='hidden' name='keyword'
					value='<c:out value="${cri.keyword }"/>'> <input
					type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
			</form>

		</div>


	</div>

</div>
<div class='bigPictureWrapper'>
	<div class='bigPicture'></div>
</div>

<style>
.uploadResult {
	width: 100%;
	background-color: white;
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

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background-color: rgba(255, 255, 255, 0.5);
	top: 0%;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
}
</style>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Files</div>
			<!-- / .panel-heading -->
			<div class="panel-body">
				<div class='uploadResult'>
					<ul>


					</ul>
				</div>
			</div>
		</div>

	</div>


</div>
<!-- 댓글 목록 처리  -->
<div class='row'>
	<div class="col-lg-12">
		<!-- /panel -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
					Reply</button>
			</div>

			<!--  panel-heading -->
			<div class="panel-body">
				<ul class="chat">
					<!-- start reply -->
					<li class="left clearfix" data-rno='12'>
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted">2023-03-25 12:42</small>
							</div>
							<p>Good ^_^~!</p>
						</div>
					</li>
					<!-- end Reply -->
				</ul>
				<!-- end ul -->

			</div>
			<!-- .panel .chat-panel -->
			<div class="panel-footer"></div>
		</div>

	</div>
	<!-- end row -->

</div>

<!--  댓글 등록 모달창  -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input class="form-control" name='reply'
						value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer'
						value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control"
						name='replyDate' value=''>
				</div>
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-default"
					data-dismiss="modal">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default"
					data-dismiss='modal'>Close</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-default"
					data-dismiss='modal'>Register</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	$(document)
			.ready(
					function() {

						var bnoValue = '<c:out value= "${board.bno}"/>';

						var replyUL = $(".chat");

						showList(1);
						//댓글목록 처리 	
						function showList(page) {

							replyService
									.getList(
											{
												bno : bnoValue,
												page : page || 1
											},

											function(replyCnt, list) {

												if (page == -1) {

													pageNum = Math
															.ceil(replyCnt / 10.0);
													showList(pageNum);
													return;

												}

												var str = "";

												if (list == null
														|| list.length == 0) {

													replyUL.html("");
													return;
												}

												for (var i = 0, len = list.length || 0; i < len; i++) {
													str += "<li class = 'left clearfix' data-rno='"+list[i].rno + "'>";
													str += "<div><div class='header'><strong class='primary-font'>"
															+ list[i].replyer
															+ "</strong>";
													str += "<small class='pull-right text-muted'>"
															+ replyService
																	.displayTime(list[i].replyDate)
															+ "</small></div>";
													str += "<p>"
															+ list[i].reply
															+ "</p></div></li>";
												}

												replyUL.html(str);

												showReplyPage(replyCnt);

											}); //end function 

						} //end showList 

						var pageNum = 1;
						var replyPageFooter = $(".panel-footer");

						// 댓글 페이지 출력 
						function showReplyPage(replyCnt) {

							var endNum = Math.ceil(pageNum / 10.0) * 10;
							var startNum = endNum - 9;

							var prev = startNum != 1;
							var next = false;

							if (endNum * 10 >= replyCnt) {

								endNum = Math.ceil(replyCnt / 10.0);
							}

							if (endNum * 10 < replyCnt) {

								next = true;
							}

							var str = "<ul class='pagination pull-right'>";

							if (prev) {
								str += "<li class='page-item'><a class='page-link' href='"
										+ (startNum - 1)
										+ "'>Previous</a></li>";
							}

							for (var i = startNum; i <= endNum; i++) {

								var active = pageNum == i ? "active" : "";

								str += "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"
										+ i + "</a></li>";
							}

							if (next) {

								str += "<li class='page-item'><a class='page-link' href='"
										+ (endNum + 1) + "'>Next</a></li>";
							}

							str += "</ul></div>";

							console.log(str);

							replyPageFooter.html(str);

						}

						//페이지 번호를 클릭할 시 새로운 댓글 가져오기 
						replyPageFooter.on("click", "li a", function(e) {

							e.preventDefault();

							console.log("page click");

							var targetPageNum = $(this).attr("href");

							console.log("targetPageNum: " + targetPageNum);

							pageNum = targetPageNum;

							showList(pageNum);

						});

						// 댓글 추가 시작 시 버튼 이벤트 처리 
						var modal = $(".modal");
						var modalInputReply = modal.find("input[name='reply']");
						var modalInputReplyer = modal
								.find("input[name='replyer']");
						var modalInputReplyDate = modal
								.find("input[name='replyDate']");

						var modalModBtn = $("#modalModBtn");
						var modalRemoveBtn = $("#modalRemoveBtn");
						var modalRegisterBtn = $("#modalRegisterBtn");

						//조회화면에 댓글 등록하기 버튼 누를 시 
						$("#addReplyBtn").on("click", function(e) {

							modal.find("input").val("");
							modalInputReplyDate.closest("div").hide();
							modal.find("button[id !='modalCloseBtn']").hide();

							modalRegisterBtn.show();

							$(".modal").modal("show");
						});

						//모달창 내 댓글 등록 버튼 누룰  
						modalRegisterBtn.on("click", function(e) {

							var reply = {
								reply : modalInputReply.val(),
								replyer : modalInputReplyer.val(),
								bno : bnoValue
							};

							replyService.add(reply, function(result) {

								alert(result);

								modal.find("input").val("");
								modal.modal("hide");

								showList(1);
							});

						});

						//댓글 클릭 이벤트 처리 
						$(".chat")
								.on(
										"click",
										"li",
										function(e) {

											var rno = $(this).data("rno");

											replyService
													.get(
															rno,
															function(reply) {

																modalInputReply
																		.val(reply.reply);
																modalInputReplyer
																		.val(reply.replyer);
																modalInputReplyDate
																		.val(
																				replyService
																						.displayTime(reply.replyDate))
																		.attr(
																				"readonly",
																				"readonly");
																modal
																		.data(
																				"rno",
																				reply.rno);

																modal
																		.find(
																				"button[id !='modalCloseBtn']")
																		.hide();
																modalModBtn
																		.show();
																modalRemoveBtn
																		.show();

																$(".modal")
																		.modal(
																				"show");
															});
										});

						//댓글 수정 
						modalModBtn.on("click", function(e) {

							var reply = {
								rno : modal.data("rno"),
								reply : modalInputReply.val()
							};

							replyService.update(reply, function(result) {

								alert(result);
								modal.modal("hide");
								//showList(1);
								//showList(-1);
								//댓글이 페이지 처리되면 댓글의 수정시 현재 댓글이 포함된 페이지로 이동하도록 수정 
								showList(pageNum);
							});

						});

						//댓글 삭제 
						modalRemoveBtn.on("click", function(e) {

							var rno = modal.data("rno");
							replyService.remove(rno, function(result) {

								alert(result);
								modal.modal("hide");
								//showList(1);
								//댓글이 페이지 처리되면 댓글삭제시 현재 댓글이 포함된 페이지로 이동하도록 수정 
								showList(pageNum);

							});

						});

					});
</script>

<script>
	/* 	console.log("======== JS TEST========");
	 var bnoValue = '<c:out value="${board.bno}"/>';
	 */
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

	/* replyService.update({
		rno :22,
		bno : bnoValue,
		reply : "Modified Reply......"
	
	}, function(result){
		alert("수정 완료 ");
	
		
		
	}); */

	/* replyService.get(10, function(data){
		console.log(data);
	});
	 */
</script>
<script>
	$(document)
			.ready(
					function() {

						(function() {

							var bno = '<c:out value="${board.bno}"/>';

							$
									.getJSON(
											"/board/getAttachList",
											{
												bno : bno
											},
											function(arr) {

												console.log(arr);

												var str = "";

												$(arr)
														.each(
																function(i,
																		attach) {

																	//image Type 
																	if (attach.fileType) {

																		var fileCallPath = encodeURIComponent(attach.uploadPath
																				+ "/s_"
																				+ attach.uuid
																				+ "_"
																				+ attach.fileName);

																		str += "<li data-path='"  +attach.uploadPath+ 
										"' data-uuid='" + attach.uuid + 
												"' data-filename='" + attach.fileName+ "' data-type='" + attach.fileType +"'><div>";
																		str += "<img src = '/display?fileName="
																				+ fileCallPath
																				+ "'>";
																		str += "<div>";
																		str
																				+ "</li>";
																	} else {

																		str += "<li data-path='" +  attach.uploadPath  +
										"' data-uuid='" + attach.uuid+ 
												"' data-filename='" + attach.fileName+ 
														"' data-type= '" + attach.fileType+ "' ><div>";
																		str += "<span>"
																				+ attach.fileName
																				+ "</span><br/>";
																		str += "<img src='/resources/img/docu.jpeg'>";
																		str += "</div>";
																		str += "</li>";
																	}

																});

												$(".uploadResult ul").html(str);

											});//end getjson

						})();//end function

						$(".uploadResult").on(
								"click",
								"li",
								function(e) {
									console.log("view image");
									var liObj = $(this);

									var path = encodeURIComponent(liObj
											.data("path")
											+ "/"
											+ liObj.data("uuid")
											+ "_"
											+ liObj.data("filename"));

									if (liObj.data("type")) {
										showImage(path.replace(new RegExp(/\\/g), "/"));
									} else {
										//download
										self.location = "/download?fileName="
												+ path

									}

								});
						
						function showImage(fileCallPath){
							alert(fileCallPath);
							$(".bigPictureWrapper").css("display","flex").show();
							$(".bigPicture").html("<img src='/display?fileName=" + fileCallPath+ "'>");
						}
						
						$(".bigPictureWrapper").on("click", function(e){
								setTimeout(function(){
									
									$('.bigPictureWrapper').hide();
								}, 300);
								
						
						});
						
						$(".bigPictureWrapper").on("click", function(e){
							
							setTimeout(function(){
								$(".bigPictureWrapper").hide();		
							},300);
							
						});
						
			
						
						
					});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {

			operForm.attr("action", "/board/modify").submit();

		});

		$("button[data-oper='list']").on("click", function(e) {

			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();

		});

	});
</script>




<%@ include file="../includes/footer.jsp"%>
