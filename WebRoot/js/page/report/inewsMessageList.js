var sort = 0;
var isDesc = 1;
var key = "";

function clickUrl(messageId,messageName){
	var agentId = $("#auth_agentId").val();
	var fromUser = $("#auth_fromUser").val();
	location.href = urlPath + "toInewsReportList?"+"&agentId="+agentId+"&fromUser="+fromUser+"&messageId="+messageId+"&messageName="+messageName;
}

var cacheHTML = "";
function loadList() {
//	$("#loading").show();
	var fromUser = $("#auth_fromUser").val();
	$.ajax({
		type:"post",
		url : urlPath + "getInewsReportList?&tmp="+getRandom(),
		data:{
			"fromUser" : fromUser
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
					.html("<div style='width:100%;line-height:35px;text-align:center;'>抱歉,暂时没有最新的报告信息</div>");
//				$("#loading").hide();
				return;
			} else {// 如果找到了数据
				var list =  eval(eval('('+resu+')'));
				var html = "";
				var messageName = "";
				for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					messageName = encodeURI(jsonA.name);
					messageName = encodeURI(messageName);
					html += "<a class='weui_cell' href='javascript:clickUrl(\""+jsonA.id+"\",\""+messageName+"\")'>" +
								"<i class='weui_icon_info'></i>"+
									"<span class='weui_cell_bd weui_cell_primary'>"+jsonA.name+"</span>"+
									"<span class='weui_cell_ft'></span>"+
					    	"</a>";
				}
				if ($("#hmList").html() == "")
					cacheHTML = "";
				cacheHTML += html;
				$("#hmList").html(cacheHTML);
//				$("#loading").hide();
			}
		},
	});
};