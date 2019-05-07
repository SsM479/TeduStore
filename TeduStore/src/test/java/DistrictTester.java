import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;

public class DistrictTester {
	@Test
	public void testMapper() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		DistrictMapper mapper = ctx.getBean("districtMapper", DistrictMapper.class);

		// 获取省列表
		System.out.println("获取省列表");
		List<Province> provinces = mapper.getProvinces();
		for (Province province : provinces) {
			System.out.println(province);
		}
		System.out.println("\n------------------------------\n");

		// 获取市列表
		System.out.println("获取市列表");
		List<City> cities = mapper.getCities("510000");
		for (City city : cities) {
			System.out.println(city);
		}
		System.out.println("\n------------------------------\n");

		// 获取区列表
		System.out.println("获取区列表");
		List<Area> areas = mapper.getAreas("510100");
		for (Area area : areas) {
			System.out.println(area);
		}
		ctx.close();
	}
	
	@Test
	public void testGetNameByCode() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		IDistrictService districtService = ctx.getBean("districtService",IDistrictService.class);

		// 获取省名称
		String provinceCode = "510000";
		System.out.println("获取省名称");
		String provinceName = districtService.getProvinceNameByCode(provinceCode);
		System.out.println("provinceName : " + provinceName);
		System.out.println("\n---------------\n");
		
		// 获取市名称
		String cityCode = "510100";
		System.out.println("获取市名称");
		String cityName = districtService.getCityNameByCode(cityCode);
		System.out.println("cityName : " + cityName);
		System.out.println("\n---------------\n");
		
		// 获取区名称
		String areaCode = "510104";
		System.out.println("获取区名称");
		String areaName = districtService.getAreaNameByCode(areaCode);
		System.out.println("areaName : " + areaName);
		System.out.println("\n---------------\n");
		
		
		ctx.close();
	}
}
