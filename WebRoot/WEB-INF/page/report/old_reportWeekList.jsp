<!DOCTYPE html>
<html>
<head>
<%@page contentType="text/html; charset=UTF-8"%>
<%
  String path = request.getContextPath();
%>
<title>历史报告</title>
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1' 
name='viewport' />
<meta content='yes' name='apple-mobile-web-app-capable' />
<meta content='black' name='apple-mobile-web-app-status-bar-style' />
<meta content='telephone=no' name='format-detection' />
<script src='/js/jquery.js'></script>
<link rel='stylesheet' href='/css/old_main.css' />
<link rel='stylesheet' href='/css/old_publicList.css' />
<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
<script src='https://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
<script type="text/javascript">
var url = "<%=path%>/report/report_getReportDownloadList";
$(function() {
	getSign();
	$("#auth_fromUser").val("${fromUser}");
	$("#auth_agentId").val("${agentId}");
	LoadList();
});

//获取签名
function getSign(){
	var pageUrl = location.href.split('#')[0];
	$.ajax({
		type:"post",
		url:"<%=path%>/wx/wx_getSignBean",
		dataType:"json",
		data:{"pageUrl":pageUrl},
		success:function(resu){
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: resu.appId, // 必填，公众号的唯一标识
			    timestamp: resu.timestamp, // 必填，生成签名的时间戳
			    nonceStr: resu.nonceStr, // 必填，生成签名的随机串
			    signature: resu.signature,// 必填，签名，见附录1
			    jsApiList: ["hideOptionMenu"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function(){
				wx.hideOptionMenu();//隐藏右上角菜单
			    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			});
		}
	});
}
</script>
<script type="text/javascript" src="/js/page/report/old_reportWeekList.js" charset="utf-8"></script>
</head>
<body>
	<input id="auth_fromUser" type="hidden"/>
	<input id="auth_agentId" type="hidden"/>
	<div id="tell"></div>
	<input id="auth_orgId" type="hidden"/>
	<jsp:include page="/WEB-INF/page/report/old_top_img_unilever.jsp"></jsp:include>
	<div class="hometitle">
		<h2>
				<span style="background:#FFF;">Unilever Weekly Report List&nbsp;</span>
		</h2>
	</div>
	<div class='home_news' style="width:100%"><ul id="hmList"></ul></div>
	<div id="loading">加载中...</div>
	<div id="getMore" onclick="LoadList()" style='width:100%;line-height:35px;text-align:center;background-color:#D1EE69;font-weight:bold;display: none;'>点击获取更多日报信息</div>
	<jsp:include page="/WEB-INF/page/report/old_bottom.jsp"></jsp:include>
</body>
</html>