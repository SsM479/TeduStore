










package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IDistrictService;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{

	@Resource
	private IDistrictService districtService;
	
	@RequestMapping("/provinces.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvince(){
		// 声明返回值
		ResponseResult<List<Province>> rr;
		
		// 通过districtService获取数据
		List<Province> provinces = districtService.getProvinces();
		
		// 创建返回值
		rr = new ResponseResult<List<Province>>(1, "获取省列表成功", provinces);
		
		return rr;
	}
	
	@RequestMapping("/cities.do")
	@ResponseBody
	public ResponseResult<List<City>> getCities(String provinceCode){
		ResponseResult<List<City>> rr;
		List<City> cities =districtService.getCities(provinceCode);
		rr = new ResponseResult<List<City>>(1, "获取市列表成功", cities);
		return rr;
	}
	
	@RequestMapping("/areas.do")
	@ResponseBody
	public ResponseResult<List<Area>> getAreas(String cityCode){
		ResponseResult<List<Area>> rr;
		List<Area> areas =districtService.getAreas(cityCode);
		rr = new ResponseResult<List<Area>>(1, "获取市列表成功", areas);
		return rr;
	}
	
}
