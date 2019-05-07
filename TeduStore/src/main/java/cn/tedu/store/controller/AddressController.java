package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.DataNotFoundException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Resource
	private IAddressService addressService;
	
	@RequestMapping("/list.do")
	public String showList() {
		return "address_list";
	}
	
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleAdd(Address address,HttpSession session){
		System.out.println("AddressController.handlerAdd()");
		System.out.println("\t address : " + address);
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 将uid封装到address中
		address.setUid(uid);
		// 调用service执行增加
		addressService.add(address);
		// 返回
		ResponseResult<Void> rr = new ResponseResult<Void>(1,"增加新收货地址成功");
		return rr;
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDelete(Integer id,HttpSession session){
		// 声明返回值
		ResponseResult<Void> rr;
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 提供过addressService执行删除，并获取结果
		try {
			addressService.delete(id, uid);
			rr = new ResponseResult<Void>(1, "删除成功");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		// 返回
		return rr;
	}
	
	@RequestMapping(value="/handle_update.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleUpdate(Address address,HttpSession session){
		// 声明返回值
		ResponseResult<Void> rr;
		// 获取uid并封装到address中
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		// 通过业务层执行修改
		Integer affectedRows = addressService.update(address);
		// 创建返回值
		if(affectedRows == 1) {
			rr = new ResponseResult<Void>(1, "修改成功");
		}else {
			rr = new ResponseResult<Void>(0, "修改失败");
		}
		return rr;
	}
	
	@RequestMapping("set_default.do")
	@ResponseBody
	public ResponseResult<Void> handleSetDefault(HttpSession session,Integer id){
		// 声明返回值
		ResponseResult<Void> rr;
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 执行
		try {
			addressService.setDefault(id, uid);
			rr = new ResponseResult<Void>(1, "设置默认成功");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		// 返回
		return rr;
	}
	
	/*
	 * ********** 查询操作才需要考虑类型 修改等都为Void *************
	 */
	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressListByUid(HttpSession session){
		ResponseResult<List<Address>> rr;
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.getAddressListByUid(uid);
		rr = new ResponseResult<List<Address>>(1,data);
		return rr;
	}
	
	@RequestMapping("/get.do")
	@ResponseBody
	public ResponseResult<Address> getAddressByIdAndUid(HttpSession session,Integer id){
		// 声明返回值
		ResponseResult<Address> rr;
		// 获取uid
		Integer uid = getUidFromSession(session);
		// 通过addressService获取数据
		Address data = addressService.getAddressByIdAndUid(id, uid);
		// 创建返回值对象
		rr = new ResponseResult<Address>(1, data);
		// 返回
		return rr;
	}
	
	
	
	
	
}
