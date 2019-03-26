
function LoadList() {
	var weekId = $("#weekId").val();
	$("#loading").show();
	$.ajax({
		type:"post",
		url:url+"getReportDetailInfo?weekId="+weekId,
		dataType:"json",
		success:function(resu){
			if (resu!=null&&resu!= "") {
				var data =  eval('('+resu+')');
				var html = "";
				var oprlink = "http\://unilever.xlmediawatch.com/cliping/cliping_toWXPage?generateId=";
				var jsonA = data;// 读取内容
				html += "<tr><td valign='top'><p class='bl3'><b>Headline:</b></td><td style='width:90%;'>";
				// ipone手机
			     if(flag==true){
			    	 if(jsonA.mediaType==1){
						html += "<a href='javascript:void(0)'  onclick='skipload(\""+jsonA.link+"\")' >"+jsonA.titleEN+"</a><br><a style='text-decoration:underline!important;width:90%;' href='javascript:void(0)'  onclick='skipload(\""+jsonA.link+"\")' >"+jsonA.titleCN+"</a>";
					}else{
						html += "<a href='javascript:void(0)' onclick='skipload(\""+oprlink+jsonA.generateId+"\")' >"+jsonA.titleEN+"</a><br><a style='width:90%;' href='javascript:void(0)' onclick='skipload(\""+oprlink+jsonA.generateId+"\")' >"+jsonA.titleCN+"</a>";
					}
			     }// 安卓等其他手机
			     else{
			    	 if(jsonA.mediaType==1){
							html += "<a href='"+jsonA.link+"'  >"+jsonA.titleEN+"</a><br><a style='text-decoration:underline!important;width:90%;' href='"+jsonA.link+"' >"+jsonA.titleCN+"</a>";
						}else{
							html += "<a href='"+oprlink+jsonA.generateId+"' >"+jsonA.titleEN+"</a><br><a style='width:90%;' href='"+oprlink+jsonA.generateId+"' >"+jsonA.titleCN+"</a>";
					 }
			     }
				
				html += "</td></tr>";
				html += "<tr><td valign='top'><p><b>Publication:</b></p></td><td>"+jsonA.sourceNameEN+"<br>"+jsonA.sourceNameCN+"</td><tr>";
				html += "<tr><td valign='top'><p><b>Date:</b></p></td><td>"+jsonA.publishTime+"</td></tr>";
				html += "<tr><td valign='top'><p><b>Tone:</b></p></td><td>"+jsonA._nature+"</td></tr>";
				html += "<tr><td valign='top' colspan='2'><p><b>Summary:</b></p></td></tr><tr><td valign='top' colspan='2'><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonA.summary+"</p></td></tr>";
				html += "<tr><td valign='top' colspan='2'><p><b>Content:</b></p></td></tr><tr><td valign='top' colspan='2' class='tdContent'>"+jsonA.content;
				
				if(jsonA._images!=null&&jsonA._images.length>0){
					for(var i=0;i<jsonA._images.length;i++){
						if(jsonA.mediaType==2){
							html += "<p align='center'><a href='" + jsonA._images[i].imageLink + "' ><img align='center' src=" + jsonA._images[i].imageLink + "></img></a></p>";
						}else{
							html += "<p align='center'><img align='center' src=" + jsonA._images[i].imageLink + "></img></p>";
						}
					}
				}
				
				html += "</td></tr>";
				$("#hmBox").empty();
				$("#hmBox").html(
						"<table cellpadding='0' cellspacing='0' border=0 style='width:100%'>"
								+ html + "</table>");
				$("#loading").hide();
			}
		}
	});
}

/*跳转到中间页面*/
function skipload(links){
	wx.showOptionMenu();//开启右上角菜单
//	window.location.href=links;
	setTimeout(function () {
		window.location.href=links;
	}, 200);
}

