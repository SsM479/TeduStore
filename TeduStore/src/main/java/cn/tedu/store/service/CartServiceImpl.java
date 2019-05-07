package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.ServiceException;

@Service("cartService")
public class CartServiceImpl implements ICartService{

	@Resource(name="cartMapper")
	private CartMapper cartMapper;
	
	public Integer add(Cart cart) {
		// 先查询有没有对应的数据
		Integer count = getRecordCount(cart.getGoodsId(), cart.getUid());
		// 判断该用户的购物车中是否已经有该商品
		if(count == 0) {
			// 该用户的购物车中尚无该商品
			// 则在购物车中添加新数据
			Integer affectedRows = cartMapper.add(cart);
			if(affectedRows == 1) {
				return 1;
			}else {
				throw new ServiceException("将商品添加到购物车时出现未知错误，请联系管理员");
			}
		}else {
			// 该用户的购物车已经有该商品
			// 则增加数量即可
			changeGoodsCount(cart.getGoodsId(), cart.getUid(), cart.getGoodsCount());
		}
		return 1;
	}

	public Integer getRecordCount(Integer goodsId, Integer uid) {
		return cartMapper.getRecordCount(goodsId, uid);
	}

	public Integer changeGoodsCount(Integer goodsId, Integer uid, Integer amount) {
		Integer affectedRows = cartMapper.changeGoodsCount(goodsId, uid, amount);
		if(affectedRows != 1) {
			throw new DataNotFoundException("购物车数据有误！请刷新后再次尝试！");
		}
		return affectedRows;
	}

	public List<Cart> getCartList(Integer uid) {
		return cartMapper.getCartList(uid);
	}

	public Cart getCartById(Integer uid, Integer id) {
		return cartMapper.getCartById(uid, id);
	}

}
