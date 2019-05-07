package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.GoodsCategory;

public interface GoodsCategoryMapper {
	
	/**
	 * 根据父级分类ID获取商品分类列表
	 * @param parentId 父级分类ID
	 * @param offset 偏移量，即跳过前面的多少条数据不要，如果从头开始获取数据应该设置为0，如果不需要分页，该参数取null
	 * @param count 获取数据的数量
	 * @return 商品分类列表，如果没有匹配的数据，则返回长度为0的集合
	 */
	List<GoodsCategory> getGoodsCategoryListByParentId(
			@Param("parentId") Integer parentId,
			@Param("offset") Integer offset,
			@Param("count") Integer count);
	
}
