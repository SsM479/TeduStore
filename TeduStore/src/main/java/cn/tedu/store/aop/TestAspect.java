package cn.tedu.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *  对业务层方法性能测试 
 */
@Component
@Aspect
public class TestAspect {
	
	/*
	 * jp对象中包含目标方法的全部信息
	 *    返回方法签名(方法名+参数列表)
	 */
	
	@Around("bean(*Service)")
	public Object test(ProceedingJoinPoint jp) throws Throwable {
		long t1 = System.currentTimeMillis();
		// 调用业务层方法
		Object val = jp.proceed();
		long t2 = System.currentTimeMillis();
		Signature s = jp.getSignature();
		System.out.println(s + "耗时:" + (t2-t1));
		return val;
	}
}
