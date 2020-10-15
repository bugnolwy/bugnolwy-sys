package cn.bugnolwy.aspect;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.service.SysLogService;
import cn.bugnolwy.util.LoginUserUtil;
import cn.bugnolwy.model.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnoLwy/bugnolwy-sys
 * @gitHub https://github.com/bugnoLwy/bugnolwy-sys
 * @since 2020-9
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Pointcut("@annotation(cn.bugnolwy.annotation.RequiredLog)")
	public void logPointCut() {
	}
	
	/**
	 * 获取方法上的注解,并拿到注解中定义的表达式
	 *
	 * @param proceedingJoinPoint 对应切入点描述的方法中的一个目标方法
	 * @return 目标方法的执行结果
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// 执行方法的开始时间
		long startTime = System.currentTimeMillis();
		
		// 执行目标方法
		Object result = proceedingJoinPoint.proceed();
		
		// 执行方法结束时间
		long endTime = System.currentTimeMillis();
		
		// 执行方法时长
		long totalTime = endTime - startTime;
		log.info("方法执行的总时长为:" + totalTime);
		
		// 执行请求的类名
		Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
		
		// 获取目标方法名
		MethodSignature ms = (MethodSignature) proceedingJoinPoint.getSignature();
		String targetClsMethod = targetClass.getName() + "." + ms.getName();
		
		// 获取接口声明的方法
		String methodName = ms.getMethod().getName();
		Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
		
		// 获取目标对象声明的方法
		Method targetMethod = targetClass.getDeclaredMethod(methodName, parameterTypes);
		
		// 获取方法对象上的注解
		RequiredLog requiredLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
		
		// 封装行为日志
		SysLog sysLog = new SysLog();
		if (requiredLog != null) {
			sysLog.setOperation(requiredLog.operation());
		}
		sysLog.setUsername(LoginUserUtil.getCurrentSysUser().getUsername());
		sysLog.setMethod(targetClsMethod);
		sysLog.setTime(totalTime);
		sysLog.setIp(LoginUserUtil.getCurrentIp());
		sysLogService.saveSysLog(sysLog);
		return result;
	}
	
}