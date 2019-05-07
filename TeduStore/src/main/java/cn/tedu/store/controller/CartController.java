package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{

	@Resource(name="cartService")
	private ICartService cartService;
	
	@RequestMapping("/list.do")
	public String showList(HttpSession session,ModelMap modelMap) {
		Integer uid = getUidFromSession(session);
		List<Cart> cartList = cartService.getCartList(uid);
		for (Cart cart : cartList) {
			System.out.println("\t" + cart);
		}
		modelMap.addAttribute("cartList",cartList);
		return "cart_list";
	}
	
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleAddToCart(Cart cart,HttpSession session){
		ResponseResult<Void> rr;
		// 从Session中获取uid
		Integer uid = getUidFromSession(session);
		// 将uid封装到cart中
		cart.setUid(uid);
		try {
			// 通过cartService执行增加
			cartService.add(cart);
			// 创建返回值，state为1
			rr = new ResponseResult<Void>(1, "加入购物车成功");
		} catch (ServiceException e) {
			// 创建返回值，state为0且message为e.getMessage()
			rr = new ResponseResult<Void>(0, e.getMessage());
		}
		// 返回
		return rr;
	}
}
