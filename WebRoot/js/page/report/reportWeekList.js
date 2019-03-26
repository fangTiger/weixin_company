var page = 0;
var sort = 0;

function clickUrl(link){
	var fromUser = $("#auth_fromUser").val();
	var agentId = $("#auth_agentId").val();
	location.href = link+"&fromUser="+fromUser+"&agentId="+agentId;
}

var cacheHTML = "";
function loadList() {
//	$("#loading").show();
	$("#getMore").hide();
	$.ajax({
		type:"post",
		url:url+"?&tmp="+getRandom(),
		data:{
			"pageNo" : page,
			"pageSize" : 10
		},
		dataType:"json",
		beforeSend:function(){
			if($("#loading").css("display")=="none")
				showCustomDiv(true, 'loading', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loading', 1, false);
			if (resu ==null ||resu == ""||resu == "\"\"") {// 如果没有数据
				$("#titleDiv")
					.html("<div style='width:100%;line-height:35px;text-align:center;'>抱歉,暂时没有最新的报告信息<</div>");
//				$("#loading").hide();
				return;
			} else {// 如果找到了数据
				var data =  eval('('+resu+')');
				page = parseInt(data.pageNow)+1;
				var maxPage = parseInt(data.pageCount)/parseInt(data.pageSize);
				var pageFlag = maxPage*parseInt(data.pageSize);
				if(parseInt(data.pageCount)>pageFlag)
					maxPage = maxPage+1;
				if(maxPage>=page)//如果还能有下一页
					$("#getMore").show();
				var html = "";
				var list = data.rows;
				for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					html += "<a class='weui_cell' href='javascript:clickUrl(\"/report/report_toReportList?reportId="+jsonA.id+"\")'>" +
								"<i class='weui_icon_info'></i>"+
								    "<span class='weui_cell_bd weui_cell_primary'>"+jsonA.fileName+"</span>"+
								    "<span class='weui_cell_ft'></span>"+
							"</a>";
				}
				if ($("#hmList").html() == "")
					cacheHTML = "";
				cacheHTML += html;
				$("#hmList").html(cacheHTML);
//				$("#loading").hide();
			}
		}
	});
}