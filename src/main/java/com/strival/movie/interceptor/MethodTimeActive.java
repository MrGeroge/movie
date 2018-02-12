package com.strival.movie.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


/**
 * 用于记录方法执行的切面
 *
 * Author:zhangyu
 * create on 15/11/5.
 */
public class MethodTimeActive implements MethodInterceptor {
    private Logger logger= LoggerFactory.getLogger(MethodTimeActive.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        /*Method method=invocation.getMethod();
        Parameter[] params=method.getParameters();
        Object[] objects=invocation.getArguments();
        StringBuilder builder=new StringBuilder();
        if(objects!=null){
            int index=0;
            for(Object o:objects){
                builder.append(";"+params[index].getName()+" is "+o.toString());
                index++;
            }
            if(StringUtils.isEmpty(builder.toString())) {
                logger.info("before invoke method name:" + invocation.getMethod().getDeclaringClass()+"."+invocation.getMethod().getName()+"()"+",method no params");
            }
            else{
                logger.info("before invoke method name:" + invocation.getMethod().getDeclaringClass()+"."+invocation.getMethod().getName()+"()" + ",method params" + builder.toString());
            }
        }
       else{
            logger.info("before invoke method name:"+invocation.getMethod().getDeclaringClass()+"."+invocation.getMethod().getName()+"()"+",method no params");
        }
        long start= System.currentTimeMillis();
*/
        Object object = invocation.proceed();

       /* long totalTime = System.currentTimeMillis() - start;
        String methodName = invocation.getMethod().getName();
        logger.info(String.format("after invoke method name: %s ,execute time:%d ms",invocation.getMethod().getDeclaringClass()+"."+methodName+"()",totalTime));
*/
        return object;
    }
}
