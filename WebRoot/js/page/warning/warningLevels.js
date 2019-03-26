
//打开跳转链接
function openLink(wlevelId) {//在这个位置上修改合买详细
	var cid = $("#auth_cid").val();
	var agentId = $("#auth_agentId").val();
	var url = urlPath+"toWarningList?agentId="+agentId+"&wlevelId="+wlevelId+"&cid="+cid;
	location.href = url;
}

//加载页面数据
function loadLevelList() {
	var cid = $("#auth_cid").val();
	$.ajax({
		type:"post",
		url:urlPath+"getWaringLevel?cid="+cid+"&tmp="+getRandom(),
		dataType:"json",
		beforeSend:function(){
			showCustomDiv(true, 'loadingToast', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loadingToast', 1, false);
			if (resu == null ||resu == ""||resu == "[]") {// 如果没有数据
				$("#warning_title").html(
						"<div style='width:100%;line-height:35px;text-align:center;'>抱歉,暂时没有最新的预警信息</div>");
				return;
			} else {// 如果找到了数据
				var list = eval(eval('(' + resu + ')'));
				var html = "";
				for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					if(jsonA.wCount>0){
						html += "<a class='weui_cell' href='javascript:openLink("+ jsonA.wLevelId + ")'>";
					}else{
						html += "<a class='weui_cell' href='javascript:showDialog()'>";
					}
					html += "<i class='weui_icon_info'></i>";
		            html += "<span class='weui_cell_bd weui_cell_primary'>";
		            html += jsonA.wLevel;
		            html += "</span>";
		            html += "<span class='weui_cell_ft'>";
		            html += jsonA.wCount+"条&nbsp;";
		            html += "</span>";
		            html += "</a>";
				}
				$("#hmList").html( html );
			}
		}
	});
}

/*关闭提示语*/
function hideDialog(){
	$("#dialog").hide();
}

/*打开提示语*/
function showDialog(){
	$("#dialog").show();
}