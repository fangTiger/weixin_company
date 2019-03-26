var page = 0;
var sort = 0;
var pageSize = 10;
var isFirst = false;
var isSecond = false;

function clickUrl(link){
	var agentId = $("#agentId").val();
	var fromUser = $("#fromUser").val();
	var weekName = $("#weekName").val();
	location.href = link+"&agentId="+agentId+"&fromUser="+fromUser+"&weekName="+weekName;
}

var cacheHTML = "";
function loadList() {
	$("#loading").show();
	$("#getMore").hide();
	var reportId = $("#reportId").val();
	$.ajax({
		type:"post",
		url : urlPath+"getReportList",
		data:{
			"pageNo" : page,
			"pageSize" : pageSize,
			"reportId" : reportId
		},
		dataType:"json",
		success:function(resu){
			if (resu ==null ||resu == ""||resu == "\"\"") {// 如果没有数据
				if ($("#hmList").html() == "")
					$("#hmList")
					.html(
							"<div style='width:100%;line-height:35px;text-align:center;background-color:#D1EE69;'>抱歉,暂时没有最新的信息</div>");
				$("#loading").hide();
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
				
				var firstList = new Array();
				var secondList = new Array();
				var fIndex = 0;
				var sIndex = 0;
				//将数据拆分为2个数据类型，此处为迁就联合利华旧版报告数据而特殊逻辑进行，其他项目请勿参考以下操作！
				$.each(list,function(index,obj){
					if(obj.firstClassId==1){
						firstList[fIndex] = obj;
						fIndex = fIndex + 1;
					}else{
						secondList[sIndex] = obj;
						sIndex = sIndex + 1;
					}
				});
				if(firstList.length>0){//第一次加载设定一级分类数据Media Monitoring
					html += "<div style='padding-left: 0.75em;padding-top: 1em;'><h2><div><span style='color: #666;background:#FFF;font-size:0.65em'>"+getNumber(1)+". Media Monitoring&nbsp;</span></div></h2></div>";
				}
				$.each(firstList,function(index,obj){
					var titleTemp = "";
					if($.trim(obj.titleEN)!="")
						titleTemp = obj.titleEN + "<br/>";
					titleTemp += obj.titleCN;
					html += "<li>";
					html += "<a style='width:90%;line-height:2.0em;' onclick='javascrapt:clickUrl(\"/report/report_toReportDetail?weekId="+obj.id+"\")'>"+titleTemp+"</a>"
					+ "<a class='fm news_in'><img src='/images/arrow.gif' /></a>"
					+ "</li>";
				});
				
				
				if(secondList.length>0 ){//第一次加载设定一级分类数据Issue Monitoring
					html += "<div style='padding-left: 0.75em;padding-top: 1em;'><h2><div><span style='color: #666;background:#FFF;font-size:0.65em'>"+getNumber(2)+". Issue Monitoring&nbsp;</span></div></h2></div>";
				}
				$.each(secondList,function(index,obj){
					var titleTemp = "";
					if($.trim(obj.titleEN)!="")
						titleTemp = obj.titleEN + "<br/>";
					titleTemp += obj.titleCN;
					html += "<li>";
					html += "<a style='width:90%;line-height:2.0em;' onclick='javascrapt:clickUrl(\"/report/report_toReportDetail?weekId="+obj.id+"\")'>"+titleTemp+"</a>"
					+ "<a class='fm news_in'><img src='/images/arrow.gif' /></a>"
					+ "</li>";
				});
				if ($("#hmList").html() == "")
					cacheHTML = "";
				cacheHTML += html;
				$("#hmList").html(
						"<table cellpadding='0' cellspacing='0' border=0 style='width:100%'>"
								+ cacheHTML + "</table>");
				isBDing = false;
				
				$("#loading").hide();
				
				/*for ( var i = 0; i < list.length; i++) {// 遍历每一条信息
					if (list[i] == "")
						continue;// 如果为空就继续
					var jsonA = list[i];// 读取内容
					if(!isFirst&&i==0){
						html += "<div style='padding-left: 0.75em;padding-top: 1em;'><h2><div><span style='color: #666;background:#FFF;font-size:0.65em'>"+getNumber(1)+". Media Monitoring&nbsp;</span></div></h2></div>";
						isFirst = true;
					}
					if(i>0&&!isSecond&&jsonA.firstClassId!=1){
						html += "<div style='padding-left: 0.75em;padding-top: 1em;'><h2><div><span style='color: #666;background:#FFF;font-size:0.65em'>"+getNumber(2)+". Issue Monitoring&nbsp;</span></div></h2></div>";
						isSecond = true;
					}
					var titleTemp = "";
					if($.trim(jsonA.titleEN)!="")
						titleTemp = jsonA.titleEN + "<br/>";
					titleTemp += jsonA.titleCN;
					
					html += "<li>";
					html += "<a style='width:90%;line-height:2.0em;' onclick='javascrapt:clickUrl(\"/report/report_toReportDetail?weekId="+jsonA.id+"\")'>"+titleTemp+"</a>"
					+ "<a class='fm news_in'><img src='/images/arrow.gif' /></a>"
					+ "</li>";
				}
				if ($("#hmList").html() == "")
					cacheHTML = "";
				cacheHTML += html;
				$("#hmList").html(
						"<table cellpadding='0' cellspacing='0' border=0 style='width:100%'>"
								+ cacheHTML + "</table>");
				isBDing = false;
				$("#loading").hide();*/
				
			}
		}
	});
}

//获取阿拉伯数字1-10/ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ
function getNumber(number){
	if(number==1){
		return "Ⅰ";
	}else if(number==2){
		return "Ⅱ";
	}else if(number==3){
		return "Ⅲ";
	}else if(number==4){
		return "Ⅳ";
	}else if(number==5){
		return "Ⅴ";
	}else if(number==6){
		return "Ⅵ";
	}else if(number==7){
		return "Ⅶ";
	}else if(number==8){
		return "Ⅷ";
	}else if(number==9){
		return "Ⅸ";
	}else if(number==10){
		return "Ⅹ";
	}
}