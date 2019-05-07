package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Address;

public interface IAddressService {
	
	/**
	 * 增加新收货地址
	 * @param address 新收货地址
	 * @return 新地址的ID
	 */
	Integer add(Address address);
	
	/**
	 * 根据id,uid删除地址信息
	 * @param id 被删除的数据的id
	 * @param uid 当前登录的用户的id
	 * @return 受影响的行数，可能是0或者1
	 * @throws 
	 */
	Integer delete(Integer id,Integer uid);
	
	/**
	 * 修改收货地址
	 * @param address 至少包括被修改的数据的ID、所归属用户的uid，和新数据
	 * @return 受影响的行数
	 */
	Integer update(Address address);
	
	/**
	 * 根据登录用户的uid查询这个用户的所有收货人地址
	 * @param uid 当前登录的用户的uid
	 * @return 这个用户的所有收货人地址
	 */
	List<Address> getAddressListByUid(Integer uid);
	
	/**
	 * 根据指定的收货地址
	 * @param id 收货地址数据的id
	 * @param uid 收货地址所归属的用户的id
	 * @return 匹配的数据，如果没有匹配的数据，则返回null
	 */
	Address getAddressByIdAndUid(@Param("id") Integer id,@Param("uid") Integer uid);
	
	/**
	 * 将某用户的某条收货地址均设置为默认
	 * @param id 用户id
	 * @param uid 改地址的id
	 * @return 设置成功，返回1，否则，发挥0，失败的原因可能因为数据已经被删除，或uid过期
	 */
	Integer setDefault(@Param("id") Integer id,@Param("uid") Integer uid);
}
