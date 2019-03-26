var tipsDiv_01 = "";
var tipsTO = 0;
var tipsT;
function showTips(tips) {
	if (tipsDiv_01 == "") {
		tipsDiv_01 = '<div class="tipsClass" id="tipsDiv_">' + tips + '</div>';
		$('body').append(tipsDiv_01);
	} else {
		byID("tipsDiv_").innerHTML = tips;
	}
	$('div.tipsClass').css({
		'top' : ($(window).height() / 2 + $(window).scrollTop()) + 'px',
		'left' : ($(window).width() - 245) / 2 + "px",
		'border' : '2px solid #E6D30A',
		'position' : 'absolute',
		'padding' : '5px',
		'background' : '#FFF588',
		'font-size' : '12px',
		'margin' : '0 auto',
		'line-height' : '25px',
		'z-index' : '100',
		'text-align' : 'center',
		'width' : '250px',
		'color' : '#6D270A',
		'opacity' : '0.95'
	});
	$('div.tipsClass').click(function() {
		$(this).hide();
	});
	$('div.tipsClass').addClass("Fillet");
	$('div.tipsClass').show();
	tipsTO = 3;
	// setTimeout(function() { $('div.tipsClass').fadeOut(); }, (3 * 1000));
	clearTimeout(tipsT);
	tipsT = setTimeout("HidTips()", 1000);
}

function byID(id) {
	return document.getElementById(id);
}
function byName(name) {
	return document.getElementsByName(name);
}

function HidTips() {
	if (tipsTO <= 0) {
		$('div.tipsClass').fadeOut();
		tipsTO = 0;
	} else {
		tipsTO--;
		tipsT = setTimeout("HidTips()", 1000);
	}
}

//div浮层控制
function showCustomDiv(state, myDiv, type, isClose) {
	if (state) {
		var width = $(document).width();
		var height = $(document).height();
		var myheigth = $(window).height();
		$("body").append("<div id='boxDiv' />");
		$("body").append("<iframe id='boxFrame' />");
		var showSetupIframe = $("#boxFrame");

		var box = $("#boxDiv");
		box.attr("style", "top:0; left:0;border:none; position:absolute; z-index:500; display:none;");
		showSetupIframe.attr("style", "top:0;background-color:#cccccc;left:0;border:none; position:absolute; z-index:1; display:none;");
		box.animate({
			opacity : 0
		}, 0);
		showSetupIframe.animate({
			opacity : 0.68
		}, 0);
		if (isClose) {
			box.click(function() {
				showCustomDiv(0, myDiv, type, 0);
			});
		}

		var showSetupDiv = $("#" + myDiv);
		showSetupIframe.show();
		showSetupIframe.height(height);
		showSetupIframe.width(width);

		box.show();
		box.height(height);
		box.width(width);

		var left = (width - parseInt(showSetupDiv.width())) / 2;
		var top = (myheigth - parseInt(showSetupDiv.height())) / 2 + $(window).scrollTop();
		showSetupDiv.css("top", top);
		showSetupDiv.css("left", left);
		showSetupDiv.show();
	} else {
		var showSetupIframe = $("#boxFrame");
		var box = $("#boxDiv");

		var showSetupDiv = $("#" + myDiv);
		if (type == 1) {
			showSetupDiv.hide();
			showSetupIframe.hide();
		} else {
			showSetupDiv.fadeOut("slow");
			showSetupIframe.fadeOut("slow");
		}
		box.unbind("click");
		box.attr("onclick","");
		box.remove();
		showSetupIframe.remove();
	}
}

/*获取随机数*/
function getRandom(){
	return Math.round(Math.random()*100000);
}