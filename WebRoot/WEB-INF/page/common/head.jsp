<%@page import="cn.system.weixin.report.common.ReportCommonData"%>
<!DOCTYPE HTML>
<html>
<%@ page contentType="text/html;charset=UTF-8"%>
<head>
<title>新联财通</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1' name='viewport' />
<meta content='yes' name='apple-mobile-web-app-capable' />
<meta content='black' name='apple-mobile-web-app-status-bar-style' />
<meta content='telephone=no' name='format-detection' />
<script type="text/javascript" src="/js/jquery.js"></script>
<%
	String logoPath="";
	String currentUser = (String)request.getAttribute("fromUser");
	if ("313".equals(currentUser)){
		logoPath = ReportCommonData.getLogo166("logo-313");
	}else{
		logoPath = ReportCommonData.getLogoXL("logo-xl");
	}
%>

<script type="text/javascript">
$(function() {
	$("#imageId").attr("src","/images/<%=logoPath%>"); 
});
</script>
</head>

<body>
    <div class="hd">
    	<h2 class="page_title"><img id="imageId" class="logo_class" src=""></img></h2>
	</div>
</body>
</html>