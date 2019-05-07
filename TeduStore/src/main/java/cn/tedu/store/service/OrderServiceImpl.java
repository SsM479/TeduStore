package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.bean.Order;
import cn.tedu.store.bean.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.ex.OrderCreateException;

@Service("orderService")
public class OrderServiceImpl implements IOrderSerivce{

	@Resource(name="orderMapper")
	private OrderMapper orderMapper;
	
	@Transactional
	public void createOrder(Order order,List<OrderItem> items) {
		// 使用变量获取每次操作的返回值
		Integer affectedRows;
		// 创建订单失败时的提示文字
		String exceptionMessage = "创建订单失败！";
		// 增加"订单数据"
		affectedRows = orderMapper.insertOrder(order);
		// 判断增加订单是否成功
		if(affectedRows != 1) {
			// 创建订单失败
			throw new OrderCreateException(exceptionMessage);
		}else {
			// 增加"订单商品"数据
			for (OrderItem orderItem : items) {
				// 执行增加，并获取返回值
				affectedRows = orderMapper.insertOrderItem(orderItem);
				// 判断增加"订单商品"是否成功
				if(affectedRows != -1) {
					throw new OrderCreateException(exceptionMessage);
				}
			}
		}
	}
}
