package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;

public interface DistrictMapper {
	/**
	 * 获取所有省的列表
	 * @return 所有省的列表
	 */
	List<Province> getProvinces();
	
	/**
	 * 获取某个省的市的列表
	 * @param provinceCode 省的编码
	 * @return 某个省的市的列表
	 */
	List<City> getCities(String provinceCode);
		
	/**
	 * 获取某个市的区列表
	 * @param cityCode 市的编码
	 * @return 某个市的区列表
	 */
	List<Area> getAreas(String cityCode);
	
	/**
	 * 根据省代码获取省名
	 * @param provinceCode 省代码
	 * @return 省名
	 */
	String getProvinceNameByCode(String provinceCode);
	
	/**
	 * 根据市代码获取市名
	 * @param provinceCode 市代码
	 * @return 市名
	 */
	String getCityNameByCode(String cityCode);
	
	/**
	 * 根据区代码获取区名
	 * @param provinceCode 区代码
	 * @return 区名
	 */
	String getAreaNameByCode(String areaCode);
	
}
