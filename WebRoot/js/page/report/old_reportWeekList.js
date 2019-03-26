var page = 0;
var sort = 0;
var isDesc = 1;
var key = "";

function openLink(link) {//在这个位置上修改合买详细
	var fromUser = $("#auth_fromUser").val();
	var agentId = $("#auth_agentId").val();
	location.href = link+"&fromUser="+fromUser+"&agentId="+agentId;
}
var cacheHTML = "";
function LoadList() {
	$("#loading").show();
	$("#getMore").hide();
	$.ajax({
		type:"post",
		url:url,
		data:{
			"pageNo" : page,
			"pageSize" : 10
		},
		dataType:"json",
		success:function(resu){
			if (resu ==null ||resu == "") {// 如果没有数据
				if ($("#hmList").html() == "")
					$("#hmList")
					.html(
							"<div style='width:100%;line-height:35px;text-align:center;background-color:#D1EE69;'>抱歉,暂时没有最新的预警信息</div>");
				$("#loading").hide();
				return;
			} else {// 如果找到了数据
				var data = eval('(' + resu + ')');
				var html = "";
				var list = data.rows;
				for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					html += "<li onclick='openLink(\"/report/report_toReportList?reportId="+jsonA.id+"\")' >";
					html += "<a style='width:90%;'>"+jsonA.fileName+"</a>"
					+ "<a class='fm news_in'><img src='/images/arrow.gif' /></a>"
					+ "</li>";
				}
				if ($("#hmList").html() == "")
					cacheHTML = "";
				cacheHTML += html;
				$("#hmList").html(
						"<table cellpadding='0' cellspacing='0' border=0 style='width:100%'>"
								+ cacheHTML + "</table>");
				$("#loading").hide();
			}
		}
	});
}