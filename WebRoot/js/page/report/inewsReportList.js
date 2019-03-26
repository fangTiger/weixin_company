var page = 1;
var pageSize = 10;
function clickUrl(link){
	location.href = link;
}

var cacheHTML = "";
function loadList() {
//	$("#loading").show();
	$("#getMore").hide();
	var messageId = $("#messageId").val();
	$.ajax({
		type:"post",
		url : urlPath + "getInewsRlistByRid?&tmp="+getRandom(),
		data:{
			"pageNo" : page,
			"pageSize" : pageSize,
			"messageId" : messageId
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
				var data =  eval('('+resu+')');
				page = parseInt(data.pageNo)+1;
				if ( data.pageNo<data.pageCount ){
					$("#getMore").show();
				}else
					$("#getMore").hide();
				var html = "";
				var list = data.rList;
				for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					html += "<a class='weui_cell' href='javascript:clickUrl(\""+jsonA.link+"\")'>" +
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
		}
	});
}