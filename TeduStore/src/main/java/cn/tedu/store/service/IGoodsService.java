package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Goods;

public interface IGoodsService {

	/*
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_DEFAULT = "priority DESC";

	/*
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_PRICE_ASC = "price ASC";

	/*
	 * 查询商品列表时默认的排序方式
	 */
	String ORDER_BY_PRICE_DESC = "price DESC";

	String[] ORDER_BY = { 
			ORDER_BY_DEFAULT, 
			ORDER_BY_PRICE_ASC, 
			ORDER_BY_PRICE_DESC 
	};

	/*
	 * 每页显示多少条数据
	 */
	Integer COUNT_PER_PAGE = 20;

	/**
	 * 设置每页的商品数量
	 * 
	 * @param countPerPage
	 */
	void setCountPerPage(int countPerPage);

	/**
	 * 获取每页显示的商品数量
	 * 
	 * @return
	 */
	Integer getCountPerPage();

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId 商品分类的ID
	 * @param orderBy    排序方式，值为SQL代码
	 * @param offset     偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count      获取数据的数量
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer offset, Integer count);

	/**
	 * 根据商品分类获取商品列表，获取到的商品数据是按照优先级排序
	 * 
	 * @param categoryId 商品分类的ID
	 * @param orderBy    排序方式，值为SQL代码
	 * @param offset     偏移量，即跳过前面的多少条数据，如果从头开始获取数据，应该设置为0，如果不需要分页，该参数取null值即可
	 * @param count      获取数据的数量
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer offset, Integer count);

	/**
	 * 根据商品分类获取商品列表，获取到的商品数据是按照优先级排序
	 * 
	 * @param categoryId 商品分类的ID
	 * @param orderBy    排序方式，值为SQL代码
	 * @param page       获取第几页的数据
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer page);

	/**
	 * 根据商品分类获取商品列表，按照默认的方式(优先级)排序
	 * 
	 * @param categoryId 商品分类的ID
	 * @param page       获取第几页的数据
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer page);

	/**
	 * 根据商品分类获取商品列表，只显示第一页的数据
	 * 
	 * @param categoryId 商品分类的ID
	 * @param orderBy    排序方式，值为SQL代码
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy);

	/**
	 * 根据商品分类获取商品列表，按照默认的方式(优先级)排序，只显示第一页的数据
	 * 
	 * @param categoryId 商品分类的ID
	 * @return 商品列表，如果没有匹配的数据，则返回长度为0的List集合
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId);
	
	/**
	 * 根据商品id获取商品信息
	 * @param id 商品id
	 * @return 商品信息，如果ID没有匹配的数据，则返回null
	 */
	Goods getGoodsById(Integer id);
	
	/**
	 * 根据商品itemType获取商品列表
	 * @param itemType
	 * @return
	 */
	List<Goods> getGoodsListByItemType(String itemType);

	/**
	 * 获取某分类的商品的数量
	 * 
	 * @param category 分类的id
	 * @return 商品的数量
	 */
	Integer getGoodsCountByCategoryId(Integer categoryId);
	
	
}
