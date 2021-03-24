package com.capstone.apm.transformer;

import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.asm.Advice;

public class RestTemplateTransformer extends AbstractTransformer{

    private final String EXPECTED_CLASS_NAME = "org.springframework.http.client.AbstractClientHttpRequest";

    private final String EXPECTED_METHOD_NAME = "execute";

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
