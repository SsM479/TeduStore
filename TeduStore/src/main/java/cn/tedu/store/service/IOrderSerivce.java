package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.service.ex.OrderCreateException;

public interface IOrderSerivce {

	/**
	 * 创建订单
	 * @param order 订单数据
	 * @param items 订单中的商品数据
	 * @throws OrderCreateException 创建订单异常
	 */
	void createOrder(Order order,List<OrderItem> items);
}
