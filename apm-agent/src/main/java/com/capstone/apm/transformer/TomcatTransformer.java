package com.capstone.apm.transformer;

import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

public class TomcatTransformer extends AbstractTransformer{

    private final String EXPECTED_CLASS_NAME = "org.apache.catalina.core.StandardEngineValve";
    private final String EXPECTED_METHOD_NAME = "invoke";

    @Override
    protected String getExpectedMethodName() {
        return EXPECTED_METHOD_NAME;
    }

    @Override
    protected String getExpectedClassName() {
        return EXPECTED_CLASS_NAME;
    }

    @Override
    protected Class<? extends Interceptor> getInterceptor() {
        return TomcatInterceptor.class;
    }

    static class TomcatInterceptor implements Interceptor {
        @Advice.OnMethodEnter
        public static long enter(@Advice.AllArguments Object[] args) {
            long enterTime = System.nanoTime();
            HttpServletRequest request = (HttpServletRequest) args[0];
            System.out.println("Request URL : " + request.getRequestURL());
            System.out.println("Client Addr : " + request.getRemoteAddr());
            System.out.println("Thread Id : " + Thread.currentThread().getId());
            return enterTime;
        }

        @Advice.OnMethodExit
        public static void exit(@Advice.Enter long enterTime) {
            long exitTime = System.nanoTime();
            long executionTime = TimeUnit.MILLISECONDS.convert(exitTime - enterTime, TimeUnit.NANOSECONDS);
            System.out.println("Method Execution Time " + executionTime + " milliseconds");
        }
    }
}
