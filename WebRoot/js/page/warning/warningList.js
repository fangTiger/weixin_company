
//打开跳转链接
function openLink(wId,type) {//在这个位置上修改合买详细
	var cid = $("#auth_cid").val();
	var agentId = $("#auth_agentId").val();
	var wlevelId = $("#auth_wlevelId").val();
	var url = urlPath+"toWarningInfo?warningId="+wId+"&typeValue="+type+"&cid="+cid+"&agentId="+agentId+"&wlevelId="+wlevelId;
	location.href = url;
}
//按照媒体类型加载页面内容
function loadMediaList(type) {
	var cid = $("#auth_cid").val();
	var wlevelId = $("#auth_wlevelId").val();
	var pageIndex = 1;
	if(type=="web"){
		pageIndex = webNum;
	}else if(type=="press"){
		pageIndex = pressNum;
	}else if(type=="app"){
		pageIndex = appNum;
	}else if(type=="weixin"){
		pageIndex = weixiNum;
	}else if(type=="broadcast"){
		pageIndex = broadcastNum;
	}else if(type=="weibo"){
		pageIndex = weiboNum;
	}
	var dateTime = $("#auth_dateTime").val();
	$.ajax({
		type:"post",
		url:urlPath+"getWarningList?tmp="+getRandom(),
		data:{
			"cid" : cid,
			"type" : type,
			"dateTime" : dateTime,
			"wlevelId" : wlevelId,
			"pageNo" : pageIndex,
			"pageSize" : 10 
		},
		dataType:"json",
		beforeSend:function(){
			if($("#loadingToast").css("display")=="none")
				showCustomDiv(true, 'loadingToast', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loadingToast', 1, false);
			var data = "";
			if(resu!=null && resu!="")
				data = eval('(' + resu + ')');
			if (data == null || data == "" || data.total == 0) {// 如果没有数据
				$("#list_"+data.type+"_title").hide();
				$("#list_"+data.type).hide();
				return;
			}
			// 如果找到了数据
			$("#list_"+data.type+"_title").show();//若有数据展示标题
			$("#list_"+data.type).show();//若有数据展示列表
			var pageNo = parseInt(pageIndex)+1;//下一页的页码
			var pageCount = parseInt(data.pageCount);//总页数
			if(pageCount>=(pageNo-1))//最后一页，则隐藏展示下页的按钮
				$("#more_"+type).remove();
			$("#list_"+type+"_title").html("<p><span>"+data.type+"("+data.total+")</span></p>");
			var htmlList = "";
			var list = data.wList;
			for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
				if (list[i] == "")
					continue;// 如果为空就继续
				var jsonA = list[i];// 读取内容
				/*if($.trim(jsonA.link)!=""){//目前数据都有link
					htmlList += "<a class='weui_cell' href='"+jsonA.link+"'>";
				}else{*/
					htmlList += "<a class='weui_cell' href='javascript:openLink(\""+ jsonA.id+ "\",\""+ jsonA.type +"\")'>";
				//}
				htmlList += "<i class='weui_icon_info'></i>";
				htmlList += "<span class='weui_cell_bd weui_cell_primary' >";
				htmlList += jsonA.title;
				htmlList += "</span>";
				htmlList += "<span class='weui_cell_ft'>";
				htmlList += "</span>";
				htmlList += "</a>";
			}
			
			if(type=="web"){
				webNum = pageNo;
			}else if(type=="press"){
				pressNum = pageNo;
			}else if(type=="app"){
				appNum = pageNo;
			}else if(type=="weixin"){
				weixiNum = pageNo;
			}else if(type=="broadcast"){
				broadcastNum = pageNo;
			}else if(type=="weibo"){
				weiboNum = pageNo;
			}
			if($("#more_"+type).length<=0&&(pageNo-1)<pageCount)
				htmlList += "<a id='more_"+type+"' class='weui_cell' href='javascript:loadMediaList(\""+type+"\")' >" +
							"<i class='weui_icon_download'></i>" +
							"<span class='weui_cell_bd weui_cell_primary'>点击查看更多信息</span>" +
							"<span class='weui_cell_ft'></span>" +
							"</a>";//直接加载更多数据
			$("#list_"+data.type).html($("#list_"+data.type).html()+htmlList);
		}
	});
}