import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTestCase {
	ClassPathXmlApplicationContext ctx;
	RedisTemplate<String, Object> template;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
		template = ctx.getBean("redisTemplate",RedisTemplate.class);
	}
	
	@Test
	public void testRedis() {
		System.out.println(template);
	}
}
