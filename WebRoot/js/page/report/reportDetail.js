function loadList() {
	var weekId = $("#weekId").val();
//	$("#loading").show();
	$.ajax({
		type:"post",
		url : urlPath+"getReportDetailInfo?&tmp="+getRandom(),
		data:{
			"weekId" : weekId
		},
		dataType:"json",
		beforeSend:function(){
			if($("#loading").css("display")=="none")
				showCustomDiv(true, 'loading', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loading', 1, false);
			if (resu!=null&&resu!= "") {
				var data =  eval('('+resu+')');
				var html = "";
				var oprlink = "http\://unilever.xlmediawatch.com/cliping/cliping_toWXPage?generateId=";
				var jsonA = data;// 读取内容
				var titleTemp = "";
				if($.trim(jsonA.titleEN)!="")
					titleTemp = jsonA.titleEN + "<br/>";
				titleTemp += jsonA.titleCN;
				html += "<tr><td><h3 class='weui_article title_span' style='padding: 1px 10px;'><b>Headline:</b></h3></td><td style='width:90%;'>";
				// ipone手机
			     if(flag==true){
			    	 if(jsonA.mediaType==1){
						html += "<a href='javascript:void(0)' style='color: #3A9ED9;' onclick='skipload(\""+jsonA.link+"\")' >"+titleTemp+"</a>";
					}else{
						html += "<a href='javascript:void(0)' style='color: #3A9ED9;' onclick='skipload(\""+oprlink+jsonA.generateId+"\")' >"+titleTemp+"</a>";
					}
			     }// 安卓等其他手机
			     else{
			    	 if(jsonA.mediaType==1){
							html += "<a href='"+jsonA.link+"' style='color: #3A9ED9;' >"+titleTemp+"</a>";
						}else{
							html += "<a href='"+oprlink+jsonA.generateId+"' style='color: #3A9ED9;' >"+titleTemp+"</a>";
					 }
			     }
				
				html += "</td></tr>";
				html += "<tr><td valign='top'><h3 class='weui_article title'><b>Publication:</b></h3></td><td><h3 class='weui_article1'>"+jsonA.sourceNameEN+"<br>"+jsonA.sourceNameCN+"</h3></td><tr>";
				html += "<tr><td valign='top'><h3 class='weui_article title'><b>Date:</b></h3></td><td><h3 class='weui_article1'>"+jsonA.publishTime+"</h3></td></tr>";
				html += "<tr><td valign='top'><h3 class='weui_article title'><b>Tone:</b></h3></td><td><h3 class='weui_article1'>"+jsonA._nature+"</h3></td></tr>";
				if ( $.trim(jsonA.titleEN)!="") {
					html += "<tr><td valign='top' colspan='2'><h3 class='weui_article title'><b>Summary:</b></h3></td></tr><tr><td valign='top' colspan='2'><article class='weui_article'><section><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonA.summary+"</p></section></article></td></tr>";
				}
				html += "<tr><td valign='top' colspan='2'><h3 class='weui_article title'><b>Content:</b></h3></td></tr><tr><td valign='top' colspan='2' class='tdContent'><article class='weui_article'><section><p>"+jsonA.content+"</p></section></article>";
				
				if(jsonA._images!=null&&jsonA._images.length>0){
					for(var i=0;i<jsonA._images.length;i++){
						if(jsonA.mediaType==2){
							html += "<article class='weui_article'><section><p align='center'><a href='" + jsonA._images[i].imageLink + "' ><img align='center' src=" + jsonA._images[i].imageLink + "></img></a></p></section></article>";
						}else{
							html += "<article class='weui_article'><section><p align='center'><img align='center' src=" + jsonA._images[i].imageLink + "></img></p></section></article>";
						}
					}
				}
				html += "</td></tr>";
				$("#hmBox").empty();
				$("#hmBox").html(
						"<table cellpadding='0' cellspacing='0' border=0 style='width:100%'>"
								+ html + "</table>");
//				$("#loading").hide();
				
				/*var html = "";
				var oprlink = "http\://unilever.xlmediawatch.com/cliping/cliping_toWXPage?generateId=";
				var jsonA = data;// 读取内容
				html += "<h3 class='weui_article title_span' style='padding: 1px 10px;'><b >Headline:</b></h3>";
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
				
				html += "<h3 class='weui_article title'><b>Publication:</b>"+jsonA.sourceNameEN+"<br>"+jsonA.sourceNameCN+"</h3>";
				html += "<h3 class='weui_article title'><b>Date:</b>"+jsonA.publishTime+"</h3>";
				html += "<h3 class='weui_article title'><b>Tone:</b>"+jsonA._nature+"</h3>";
				html += "<h3 class='weui_article title'><b>Summary:</b></h3>";
				html += "<article class='weui_article'><section><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonA.summary+"</p></section>";
				html += "<h3 class='weui_article title'><b>Content:</b></h3>";
				html += "<article class='weui_article'><section><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonA.content+"</p>";
				
				if(jsonA._images!=null&&jsonA._images.length>0){
					for(var i=0;i<jsonA._images.length;i++){
						if(jsonA.mediaType==2){
							html += "<p align='center'><a href='" + jsonA._images[i].imageLink + "' ><img align='center' src=" + jsonA._images[i].imageLink + "></img></a></p>";
						}else{
							html += "<p align='center'><img align='center' src=" + jsonA._images[i].imageLink + "></img></p>";
						}
					}
				}
				html += "</section>";
				$("#hmBox").empty();
				$("#hmBox").html(
						"<div class='bd' style='margin-top:0.5em;'>"+ html + "</div>");
				$("#loading").hide();
				*/
			}
		}
	});
}

/*跳转到中间页面*/
function skipload(links){
	wx.showOptionMenu();//开启右上角菜单
	setTimeout(function () {
		window.location.href=links;
	}, 200);
}

