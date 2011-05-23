package com.shopzilla.perf.aspect;

import javax.annotation.Resource;

import com.shopzilla.perf.logger.LogLevel;
import com.shopzilla.perf.logger.Logger;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static String BEFORE_STRING = "[ entering < {0} > ]";
	
	private static String BEFORE_WITH_PARAMS_STRING = "[ entering < {0} > with params {1} ]";

	private static String AFTER_THROWING = "[ exception thrown < {0} > exception message {1} with params {2} ]";

	private static String AFTER_RETURNING = "[ leaving < {0} > returning {1} ]";

	private static String AFTER_RETURNING_VOID = "[ leaving < {0} > ]";

	@Resource
	private Logger logger;

	@Before(value = "@annotation(trace)", argNames = "joinPoint, trace")
	public void before(JoinPoint joinPoint, Loggable loggable) {

		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		String name = joinPoint.getSignature().getName();
		
		if (ArrayUtils.isEmpty(joinPoint.getArgs())) {
			logger.log(loggable.value(), clazz, null, BEFORE_STRING, name,
				constructArgumentsString(clazz, joinPoint.getArgs()));
		} else {
			logger.log(loggable.value(), clazz, null, BEFORE_WITH_PARAMS_STRING, name,
					constructArgumentsString(clazz, joinPoint.getArgs()));			
		}
	}

	@AfterThrowing(value = "@annotation(com.shopzilla.perf.aspect.Loggable)",
			throwing = "throwable", argNames = "joinPoint, throwable")
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {

		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		String name = joinPoint.getSignature().getName();
		logger.log(LogLevel.ERROR, clazz, throwable, AFTER_THROWING, name,
				throwable.getMessage(), constructArgumentsString(clazz,
						joinPoint.getArgs()));
	}

	@AfterReturning(value = "@annotation(trace)", returning = "returnValue",
			argNames = "joinPoint, trace, returnValue")
	public void afterReturning(JoinPoint joinPoint, Loggable loggable,
			Object returnValue) {

		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		String name = joinPoint.getSignature().getName();

		if (joinPoint.getSignature() instanceof MethodSignature) {
			MethodSignature signature = (MethodSignature) joinPoint
					.getSignature();
			Class<?> returnType = signature.getReturnType();
			if (returnType.getName().compareTo("void") == 0) {
				logger.log(loggable.value(), clazz, null, AFTER_RETURNING_VOID,
						name, constructArgumentsString(clazz, returnValue));

				return;
			}
		}

		logger.log(loggable.value(), clazz, null, AFTER_RETURNING, name,
				constructArgumentsString(clazz, returnValue));
	}

	private String constructArgumentsString(Class<?> clazz, Object... arguments) {

		StringBuffer buffer = new StringBuffer();
		for (Object object : arguments) {

			buffer.append(object);
		}
		
		return buffer.toString();
	}
}
