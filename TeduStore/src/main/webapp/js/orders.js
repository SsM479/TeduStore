/**
 * Created by yy on 2016/12/15.
 */

$("#leftsidebar_box dt").css({
	"background-color" : " #0AA1ED"
});
$(function() {
	// 隐藏所有块中的所有菜单项
	$("#leftsidebar_box dd").hide();
	// 显示"我的订单"的所有菜单项
	$("#leftsidebar_box .my_order dd").show();
	// 所有块中的所有标题绑定点击事件
	$("#leftsidebar_box dt").click(
			function() {
				// 所有块中的所有标题的背景颜色设置为#0AA1ED
				$("#leftsidebar_box dt").css({
					"background-color" : "#0AA1ED"
				});
				// 被点击的标题的背景颜色设置为#0AA1ED
				$(this).css({
					"background-color" : "#0AA1ED"
				});
				// 被点击的标题所属的区块查找所有列表项移除样式"menu_choice"
				$(this).parent().find('dd').removeClass("menu_chioce");
				// 所有标题的最右侧显示的箭头设置为“向下”
				$("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder1.png");
				// 被点击的标题所属的区块的最右侧显示的箭头设置为“向右”
				$(this).parent().find('img').attr("src","../images/myOrder/myOrder2.png");
				// 将样式为menu_chioce的标签收起
				$(".menu_chioce").slideUp();
				// 被点击的标题所属的区块中的所有列表项切换展开/收起
				$(this).parent().find('dd').slideToggle();
				// 被点击的标题所属的区块中的所有列表项添加样式menu_chioce
				$(this).parent().find('dd').addClass("menu_chioce");
				// 被点击的标题所属的区块的兄弟层级标签（其它区块）的子级的所有列表项收起
				$(this).parent().siblings().children('dd').slideUp();
			});
})
// 分页部分
$(".tcdPageCode").createPage({
	pageCount : 6,
	current : 1,
	backFn : function(p) {

	}
});
