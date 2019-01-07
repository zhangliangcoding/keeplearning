package com.learning.zuul;

/**
 * @Auther: zhangll
 * @Date: 2019/1/5 16:12
 * @Description:
 */
public class ZuulRequestContext {

    protected static Class<? extends ZuulRequestContext> contextClass = ZuulRequestContext.class;

    protected static final ThreadLocal<? extends ZuulRequestContext> threadLocal = new ThreadLocal<ZuulRequestContext>(){
        @Override
        protected ZuulRequestContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    };




}
