import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistsException;
import cn.tedu.store.service.ex.UsernameNotFoundException;

public class UserTester {
	
	@Test
	public void testServiceChangePassword() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		IUserService userService = ctx.getBean("userService", IUserService.class);

		try {
			Integer uid = 4;
			String oldPassword = "123456";
			String newPassword = "mybatis888";
			Integer affectedRows = userService.changePassword(uid, oldPassword, newPassword);
			System.out.println("受影响的行数=" + affectedRows);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		} catch ( PasswordNotMatchException e) {
			System.out.println(e.getMessage());
		}

		ctx.close();
	}

	@Test
	public void testServiceLogin() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		IUserService userService = ctx.getBean("userService", IUserService.class);

		try {
			String username = "tomcat";
			String password = "P@ssw0rd";
			User u = userService.login(username, password);
			System.out.println("登录成功！user=" + u);
		} catch (UsernameNotFoundException e) {
			System.out.println(e.getMessage());
		} catch ( PasswordNotMatchException e) {
			System.out.println(e.getMessage());
		}

		ctx.close();
	}

	@Test
	public void testMapperInsert() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");

		UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);

		User user = new User();
		user.setUsername("spring");
		user.setPassword("s3cret");
		userMapper.insert(user);
		System.out.println("增加数据完成:" + user);

		User u = userMapper.findUserByUsername(user.getUsername());
		System.out.println("查询用户:" + u);

		ctx.close();
	}

	@Test
	public void testServiceRegister() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		IUserService userService = ctx.getBean("userService", IUserService.class);

		User user = new User();
		user.setUsername("jQuery");
		user.setPassword("javascript");

		try {
			Integer id = userService.register(user);
			System.out.println("注册成功，ID=" + id);
		} catch (UsernameAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}

		ctx.close();
	}

	@Test
	public void testServiceCheckUsername() {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");

		IUserService userService = ctx.getBean("userService", IUserService.class);

		boolean flag = userService.checkUsernameExists("j");
		System.out.println(flag);

		ctx.close();
	}

}
