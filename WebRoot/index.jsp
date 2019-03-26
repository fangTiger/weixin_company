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
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.4.2/weui.css" type="text/css"></link>
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/0.4.2/weui.min.css" type="text/css"></link>
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<script type="text/javascript" src="js/common.js"></script>
  	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  <body>
  	<jsp:include page="/WEB-INF/page/common/head.jsp"></jsp:include>
   	<div class="container">
   		<div class="article">
			<div class="bd" style="margin-top:0.5em;">
				<h3 class="weui_article title_span" style="padding: 1px 10px;"><b >标题：</b>换胎很困难，烧胎补好吗？换胎很困难，烧胎补好吗？</h3>
            	<h3 class="weui_article title"><b>时间：</b>2016/6/21 13:26:13</h3>
            	<h3 class="weui_article title"><b>摘要：</b></h3>
	    		<article class="weui_article">
			        <section>
			            <section>
			                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; @NewsTurbo第一如果补胎测试过没问题，可以补胎，第二换胎最好换一对轮胎，保证一对胎是一样才能保证原厂的刹车还有ESP正常工作，可参考49期ASK。YYP说的，第三可以买一些其他品牌的便宜轮胎代替，规格必须是185/60R15的就行。这个档次的家用轮胎，国产完全可以胜任啊，就是买朝阳的高端，也没多少钱，。
			                </p>
			            </section>
			            <h3 class="title"><b>来源：</b><a href="index_warning_org.jsp;" style="color: #3A9ED9;">新车评</a></h3>
			        </section>
	    		</article>
			</div>
		</div>
		<!-- 正文尾部二维码部分 -->
		<article class="weui_article" >
		        <section>
		            <section>
		            	<p style="padding: 10px 0px;">
		            		<img src="images/xlct.jpg"></img>
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
  </body>
</html>
