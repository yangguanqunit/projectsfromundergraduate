$(function() {
	//----------------------静态样式----------------------------
	$(".leftsidebar_box dt").css({
		"background-color": "#3992d0"
	});
	$(".leftsidebar_box dt img").attr("src", "images/left/select_xl01.png");
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function() {
		$(".leftsidebar_box dt").css({
			"background-color": "#3992d0"
		});
		$(this).css({
			"background-color": "#317eb4"
		});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src", "images/left/select_xl01.png");
		$(this).parent().find('img').attr("src", "images/left/select_xl.png");
		$(".menu_chioce").slideUp();
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
	
	//--------------------自动分配ip-----------------------------
	$('#btn_0').on('click',function(){
		alert('aaa');
	})
	//--------------------动态分配ip-----------------------------
	//--------------------手工分配ip-----------------------------
	
	
})