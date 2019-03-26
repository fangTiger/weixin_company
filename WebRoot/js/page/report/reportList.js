var page = 0;
var pageSize = 10;
var sort = 0;
var isFirst = false;
var isSecond = false;

function clickUrl(link){
	var agentId = $("#agentId").val();
	var fromUser = $("#fromUser").val();
	location.href = link+"&agentId="+agentId+"&fromUser="+fromUser;
}

var cacheHTML = "";
function loadList() {
//	$("#loading").show();
	$("#getMore").hide();
	var reportId = $("#reportId").val();
	$.ajax({
		type:"post",
		url : urlPath+"getReportList?&tmp="+getRandom(),
		data:{
			"pageNo" : page,
			"pageSize" : pageSize,
			"reportId" : reportId
		},
		dataType:"json",
		beforeSend:function(){
			if($("#loading").css("display")=="none")
				showCustomDiv(true, 'loading', 1, false);//数据加载中
		},
		success:function(resu){
			showCustomDiv(false, 'loading', 1, false);
			if (resu ==null ||resu == ""||resu == "\"\"") {// 如果没有数据
				$("#titleDiv").show();
//				$("#loading").hide();
				return;
			} else {// 如果找到了数据
				$("#titleDiv").hide();
				var data =  eval('('+resu+')');
				page = parseInt(data.pageNow)+1;
				var maxPage = parseInt(data.pageCount)/parseInt(data.pageSize);
				var pageFlag = maxPage*parseInt(data.pageSize);
				if(parseInt(data.pageCount)>pageFlag)
					maxPage = maxPage+1;
				if(maxPage>=page)//如果还能有下一页
					$("#getMore").show();
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
				if(firstList.length>0 && $("#clzDiv1").length<=0){//第一次加载设定一级分类数据Media Monitoring
					var topDiv = "<div class=\"weui_cells_title\"><p><span>"+getNumber(1)+". Media Monitoring&nbsp;</span></p></div><div id='clzDiv1' class=\"weui_cells weui_cells_access\"></div>";
					$("#hmList").append(topDiv);
				}
				$.each(firstList,function(index,obj){
					addRowData(obj,"clzDiv1");
				});
				if(secondList.length>0 && $("#clzDiv2").length<=0){//第一次加载设定一级分类数据Issue Monitoring
					var topDiv = "<div class=\"weui_cells_title\"><p><span>"+getNumber(2)+". Issue Monitoring&nbsp;</span></p></div><div id='clzDiv2' class=\"weui_cells weui_cells_access\"></div>";
					$("#hmList").append(topDiv);
				}
				$.each(secondList,function(index,obj){
					addRowData(obj,"clzDiv2");
				});
//				$("#loading").hide();
			}
		}
	});
}

/* 填入行内数据 */
function addRowData(jsonA,clzDivId){
	var titleTemp = "";
	if($.trim(jsonA.titleEN)!="")
		titleTemp = jsonA.titleEN + "<br/>";
	titleTemp += jsonA.titleCN;
	$("#"+clzDivId).append("<a class='weui_cell' href='javascript:clickUrl(\"/report/report_toReportDetail?weekId="+jsonA.id+"\")'>" +
			"<i class='weui_icon_info'></i>"+
			"<span class='weui_cell_bd weui_cell_primary'>"+titleTemp+"</span>"+
			"<span class='weui_cell_ft'></span>"+
		"</a>");
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