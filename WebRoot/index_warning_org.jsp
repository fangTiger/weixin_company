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
	    <div class="cell">
		    <div class="hd">
			</div>
	    </div>
	    <div class="bd spacing">
    <a href="javascript:;" class="weui_btn weui_btn_primary" id="showToast">点击弹出Toast</a>
    <a href="javascript:;" class="weui_btn weui_btn_primary" id="showLoadingToast">点击弹出Loading Toast</a>
</div>
<div id="loadingToast" class="weui_loading_toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content">数据加载中</p>
    </div>
</div>
	     <!-- 列表内容start -->
	    <div class="weui_cells_title"><p><span>预警信息</span></p></div>
	    <div class="weui_cells weui_cells_access">
	        <a class="weui_cell" href="index_report.jsp">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	途虎靠谱？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	电瓶貌似中招了，大家看看是这么回事不？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	车助网陪你选购正品进口汽车配件
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	卖轮胎起家的“途虎养车网”，现在也要做起保养了
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	途虎靠谱？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	电瓶貌似中招了，大家看看是这么回事不？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	车助网陪你选购正品进口汽车配件
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	卖轮胎起家的“途虎养车网”，现在也要做起保养了
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	        <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	途虎靠谱？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	电瓶貌似中招了，大家看看是这么回事不？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	车助网陪你选购正品进口汽车配件
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	卖轮胎起家的“途虎养车网”，现在也要做起保养了
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	    </div>
	    <div class="weui_cells_title"><p><span>预警信息</span></p></div>
	    <div class="weui_cells weui_cells_access">
	        <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	途虎靠谱？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	电瓶貌似中招了，大家看看是这么回事不？
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	车助网陪你选购正品进口汽车配件
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	         <a class="weui_cell" href="javascript:;">
	            <i class="weui_icon_info"></i>
	            <span class="weui_cell_bd weui_cell_primary">
	                	卖轮胎起家的“途虎养车网”，现在也要做起保养了
	            </span>
	            <span class="weui_cell_ft">
	            </span>
	        </a>
	    </div>
	     <!-- 列表内容end -->
	    <!-- 获取更多信息start -->
	    <div>
		   <div class="more_msg">
	    	<a class="weui_cell" href="index.jsp">
	    	<img src="images/down-icon.png"></img>
            <span class="weui_cell_bd weui_cell_primary">
                	点击查看更多信息
            </span>
        	</a>
			</div>
	    </div>
	    <!-- 获取更多信息end -->
   </div>
  </body>
</html>
