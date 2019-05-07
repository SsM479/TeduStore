import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;


public class CartTest {
	@Test
	public void testMapperAdd() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		ICartService cartService = ctx.getBean("cartService",ICartService.class);
		
		Cart cart = new Cart();
		cart.setUid(9527);
		cart.setGoodsId(100);
		cart.setGoodsImage("image url");
		cart.setGoodsTitle("Thinkpad x1");
		cart.setGoodsItemType("ThinkPad");
		cart.setGoodsPrice(9888);
		cart.setGoodsCount(5);
		Integer id = cartService.add(cart);
		
		System.out.println("cart id=" + id);

		ctx.close();
	}
	
	@Test
	public void testGetCount() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		ICartService cartService = ctx.getBean("cartService",ICartService.class);
		
		Integer goodsId = 10000022;
		Integer uid = 9527;
		Integer count = cartService.getRecordCount(goodsId, uid);
		System.out.println("count=" + count);

		ctx.close();
	}
	
	@Test
	public void testChangeGoodsCount() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		ICartService cartService = ctx.getBean("cartService",ICartService.class);
		
		Integer uid = 8;
		Integer goodsId = 10000022;
		Integer amount = -2;
		Integer affectedRows = cartService.changeGoodsCount(goodsId, uid, amount);
		System.out.println("affectedRows=" + affectedRows);

		ctx.close();
	}
	
	@Test
	public void testGetCartList() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		CartMapper mapper = ctx.getBean("cartMapper",CartMapper.class);
		
		Integer uid = 8;
		List<Cart> cartList = mapper.getCartList(uid);
		for (Cart cart : cartList) {
			System.out.println(cart);
		}

		ctx.close();
	}
}
