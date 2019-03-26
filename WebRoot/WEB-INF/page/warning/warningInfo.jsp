<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>预警信息</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1' name='viewport' />
	<meta content='yes' name='apple-mobile-web-app-capable' />
	<meta content='black' name='apple-mobile-web-app-status-bar-style' />
	<meta content='telephone=no' name='format-detection' />
	<script type="text/javascript" src="js/jquery.js"></script>
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.4.2/weui.css" type="text/css"></link>
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.4.2/weui.min.css" type="text/css"></link>
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<script type="text/javascript" src="js/common.js"></script>
	<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
	<script type="text/javascript" src="js/page/warning/warningInfo.js"></script>
  	<script type="text/javascript">
		var url = "<%=path%>/warning/warning_getWarningInfo";
		
		$(function() {
			getSign();
			$("#auth_warningId").val("${warningId}");
			$("#auth_type").val("${typeValue}");
			loadInfo();//加载详细内容
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
					wx.error(function(res){
		    			// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					});
				}
			});
		}
	</script>
  	
  </head>
  <body>
	<input id="auth_warningId" type="hidden"/>
	<input id="auth_type" type="hidden"/>
  	<jsp:include page="/WEB-INF/page/common/head.jsp"></jsp:include>
   	<div class="container">
   		<div class="article">
			<div class="bd" style="margin-top:0.5em;" id="hmBox">
			</div>
		</div>
		<!-- 正文尾部二维码部分 -->
		<article class="weui_article" >
		        <section>
		            <section>
		            	<p style="padding: 10px 0px;">
		            		<img src="/images/xlct.jpg"></img>
		            	</p>
			            <p style="line-height: 25.6px; white-space: normal;">
			            	<span>
	            				<span style="font-size: 18px;">
		            				<strong>
			            				<span style="font-family: 微软雅黑;">i无界 新联动
			            					<span id="transmark"></span>
			            				</span>
		            				</strong>
	            				</span>
			            	</span>
			            </p>
		                <p style="line-height: 25.6px; white-space: normal;">
		                	<strong>
		                		<span style="font-family: 微软雅黑;">微博：</span>
		                	</strong>
		                	<span style="font-family: 微软雅黑;">@新联媒体监测</span>
		                </p>
		                <p style="line-height: 25.6px; white-space: normal;">
		                	<strong>
		                		<span style="font-family: 微软雅黑;">英文资讯账号：</span>
		                	</strong>
		                	（
		                	<span style="text-decoration: underline; font-family: 微软雅黑; font-weight: bold;">NewsTurbo</span>
			                	<span style="font-family: 微软雅黑;">&nbsp;长按复制）</span>
		                </p>	
		            </section>
		        </section>
    	</article>
	</div>
	<div id="loadingToast" class="weui_loading_toast" style="display: none;">
		    <div class="weui_mask_transparent"></div>
		    <div class="weui_toast">
		        <div class="weui_loading">
		        	<img src="/images/loading_data.gif"></img>
		        </div>
		        <p class="weui_toast_content">数据加载中</p>
			</div>
		</div>
  </body>
</html>
