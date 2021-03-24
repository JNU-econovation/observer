package com.capstone.apm.transformer;

import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.asm.Advice;

public class RestTemplateTransformer extends AbstractTransformer{
    @Override
    protected String getExpectedMethodName() {
        return "execute";
    }

    @Override
    protected String getExpectedClassName() {
        return "org.springframework.http.client.AbstractClientHttpRequest";
    }

    @Override
    protected Class<? extends Interceptor> getInterceptor() {
        return RestTemplateInterceptor.class;
    }


    static class RestTemplateInterceptor implements Interceptor {
        @Advice.OnMethodEnter
        private static void enter() {
            System.out.println("RestTemplate Request Start");
        }

        @Advice.OnMethodExit
        private static void exit() {
            System.out.println("RestTemplate Request End");
        }
    }
}
