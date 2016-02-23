package com.dbsys.rs.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbsys.rs.EntityRestMessage;
import com.dbsys.rs.ListEntityRestMessage;
import com.dbsys.rs.RestMessage;

@Aspect
@Component
public class MessageConverter {
	
	@Around("execution(public com.dbsys.rs.RestMessage com.dbsys.rs.*.controller.*.*(..) throws com.dbsys.rs.ApplicationException, javax.persistence.PersistenceException)")
	public @ResponseBody RestMessage process(ProceedingJoinPoint jointPoint) {
		try {
			return (RestMessage) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return RestMessage.error((Exception)e);
		}
	}

	@Around("execution(public com.dbsys.rs.EntityRestMessage com.dbsys.rs.*.controller.*.*(..) throws com.dbsys.rs.ApplicationException, javax.persistence.PersistenceException)")
	public @ResponseBody EntityRestMessage<?> getEntity(ProceedingJoinPoint jointPoint) {
		try {
			return (EntityRestMessage<?>) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return EntityRestMessage.entityError((Exception)e);
		}
	}
	
	@Around("execution(public com.dbsys.rs.ListEntityRestMessage com.dbsys.rs.*.controller.*.*(..) throws com.dbsys.rs.ApplicationException, javax.persistence.PersistenceException)")
	public @ResponseBody ListEntityRestMessage<?> getListEntity(ProceedingJoinPoint jointPoint) {
		try {
			return (ListEntityRestMessage<?>) jointPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return ListEntityRestMessage.listEntityError((Exception)e);
		}
	}
}
