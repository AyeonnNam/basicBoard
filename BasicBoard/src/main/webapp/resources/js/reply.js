console.log("reply Module........");

var replyService= (function(){

	function add(reply, callback, error){
		
	
	$.ajax({
		type: 'post',
		url : '/replies/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success :  function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
	})
  }
  
  	//댓글 페이지의 화면 처리 
	// 게시물을 조회하는 페이지에 들어올 시, 기본적으로 가장 오래된 댓글들을 가져와서 1p에 보여줌 
	// 1p의 게시물을 가져올 시, 해당 게시물의 댓글의 숫자를 파악해서  댓글의 페이지 번호 출력 
	// 댓글이 추가되면 댓글의 수자만을 가져와서 최종 페이지를 찾아 이동 
	// 댓글의 수정과 삭제 후에는 다시 동일 페이지 호출 
	function getList(param, callback, error){
		
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/"+bno+"/" + page + ".json",
			function(data){
				if(callback){
				//callback(data);
				callback(data.replyCnt, data.list);
				}
			}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		
		});
	
	}  
	
	function remove(rno, callback, error){
		$.ajax({
			type: 'delete',
			url: '/replies/' +  rno,
			success : function (deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
				}
			}, 
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function update(reply, callback, error){
		console.log("RNO:" + reply.rno);
		
		$.ajax({
			type:'put',
			url:'/replies/'+reply.rno,
			data: JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
				callback(result);
				}
			}, 
			error :  function(xhr, status, er){
				if(error){
					error(er);
					}
			}
		
		});
	}
	
	function get(rno, callback, error){
		$.get("/replies/"+ rno +".json", function(result){
			if(callback){
				callback(result);
			}
			
		}).fail(function(xhr, status, err){
			if(error){
				 error();
				 }
		
		});
	}


	function displayTime(timeValue){
			
			var today = new Date();
			var gap = today.getTime() - timeValue;
			var dateObj = new Date(timeValue);
			var str ="";
			
			if(gap < (1000 * 60 * 60 * 24)){
				var hh = dateObj.getHours();
				var mi = dateObj.getMinutes();
				var ss = dateObj.getSeconds();
				
				return [ (hh > 9 ? '' : '0') + hh, ':',(mi > 9? '' : '0') + mi, ':',(ss>9?'':'0') +ss ].join('');
			}else {
			
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() +1 ;
			var dd = dateObj.getDate();
			
				return [ yy, '/', (mm > 9 ? '': '0') + mm, '/', (dd > 9? '': '0') +dd ].join('');
			
			}
	
		}
	
	;
	
	
  
	
	return {add:add,
	getList: getList,
	remove: remove,
	update: update,
	get: get,
	displayTime: displayTime
	};

})();