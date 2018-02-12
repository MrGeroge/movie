package com.strival.movie.util;



import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xinghai on 2015/11/10.
 */
public class LogAspect {
    private final static Logger logger= LoggerFactory.getLogger(LogAspect.class);
    public void recordLog(){
        logger.info("record log");
    }

    public void before() {
        logger.info("已经记录下操作日志@Before 方法执行前");
    }
/*
    public void around(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("已经记录下操作日志@Around 方法执行前");
        pjp.proceed();
        logger.info("已经记录下操作日志@Around 方法执行后");
    }
*/
    public void after() {
        logger.info("已经记录下操作日志@After 方法执行后");
    }

}
