<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- 左边栏-->
<div id="leftsidebar_box" class="lf">
	<div class="line"></div>
	
	<!-- 我的订单 -->
	<dl class="my_order">
		<!-- 标题 -->
		<dt>
			我的订单 <img src="../images/myOrder/myOrder2.png">
		</dt>
		<dd class="first_dd">
			<a href="orders.html">全部订单</a>
		</dd>
		<dd>
			<a href="#"> 待付款 <span> <!--待付款数量--></span></a>
		</dd>
		<dd>
			<a href="#"> 待收货 <span> <!--待收货数量-->1</span></a>
		</dd>
		<dd>
			<a href="#"> 待评价<span> <!--待评价数量--></span></a>
		</dd>
		<dd>
			<a href="#">退货退款</a>
		</dd>
	</dl>

	<!-- 我的优惠券 -->
	<dl class="footMark">
		<dt>
			我的优惠卷<img src="../images/myOrder/myOrder1.png">
		</dt>
	</dl>
	
	<!-- 收货地址 -->
	<dl class="address">
		<dt>
			收货地址<img src="../images/myOrder/myOrder1.png">
		</dt>
		<dd>
			<a href="../address/list.do">地址管理</a>
		</dd>
	</dl>
	
	<!-- 账号管理 -->
	<dl class="count_managment">
		<dt>
			帐号管理<img src="../images/myOrder/myOrder1.png">
		</dt>
		<dd class="first_dd">
			<a href="../user/profile.do">我的信息</a>
		</dd>
		<dd>
			<a href="../user/password.do">安全管理</a>
		</dd>
	</dl>
</div>