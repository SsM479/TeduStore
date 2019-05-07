package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Cart;

public interface CartMapper {
	
	/**
	 * 将商品加入到购物车
	 * @param cart 购物车信息
	 * @return 受影响的行数
	 */
	Integer add(Cart cart);
	
	/**
	 *  获取记录数量
	 * @param goodsId 商品id
	 * @param uid 用户id
	 * @return 记录的数量，由于业务层应该维护对应的业务逻辑，返回值应该是0或1
	 */
	Integer getRecordCount(@Param("goodsId") Integer goodsId,@Param("uid") Integer uid);
	
	/**
	 * 调整购物车某商品的数量
	 * @param amount 修改数量
	 * @param goodsId 商品id
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	Integer changeGoodsCount(@Param("goodsId") Integer goodsId,@Param("uid") Integer uid,@Param("amount") Integer amount);
	
	/**
	 * 获取购物车列表
	 * @param uid 用户id
	 * @return 购物车列表
	 */
	List<Cart> getCartList(Integer uid);
	
	/**
	 * 根据id获取购物车中的商品信息
	 * @param id 购物车中的数据id
	 * @param uid 用户id
	 * @return 获取到的信息
	 */
	Cart getCartById(@Param("uid") Integer uid,@Param("id") Integer id);
}
