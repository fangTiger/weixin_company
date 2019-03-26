
//加载页面内容
function loadInfo() {
	var warningId = $("#auth_warningId").val();
	var type = $("#auth_type").val();
	$.ajax({
		type:"post",
		url:url+"?warningId="+warningId+"&type="+type+"&tmp="+getRandom(),
		dataType:"json",
		beforeSend:function(){
			if($("#loadingToast").css("display")=="none")
				showCustomDiv(true, 'loadingToast', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loadingToast', 1, false);
			var data = eval('(' + resu + ')');
			var html = "";
			html += "<h3 class='weui_article title_span' style='padding: 1px 10px;'><b >标题：</b>"+data.title+"</h3>";
			html += "<h3 class='weui_article title'><b>时间：</b>"+data.time+"</h3>";
			if(data.summary!=null&&$.trim(data.summary)!=""){//若摘要不为空，则展示摘要部分
				html += "<h3 class='weui_article title'><b>摘要：</b></h3>";
				html += "<article class='weui_article'>";
				html += "<section>";
				html += "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data.summary+"</p>";
				html += "</section>";
			}else{
				html += "<article class='weui_article'>";
			}
			if(data.link==null||$.trim(data.link)=="")
				html += "<h3 class='title'><b>来源：</b>"+data.source+"</h3>";
			else
				html += "<h3 class='title'><b>来源：</b><a href='"+data.link+"' style='color: #3A9ED9;'>"+data.source+"</a></h3>";
			html += "</article>";
			$("#hmBox").html(html);
		}
	});
}