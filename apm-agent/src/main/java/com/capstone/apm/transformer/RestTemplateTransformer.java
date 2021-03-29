package com.capstone.apm.transformer;

import com.capstone.apm.transaction.TransactionContext;
import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.asm.Advice;

import java.util.List;
import java.util.Map;

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
        private static void enter(@Advice.FieldValue("headers") Map<String, List<String>> headers) {
            TransactionContext.getTransactionContext().modifyHeader(headers);
        }

        @Advice.OnMethodExit
        private static void exit() {
            System.out.println("RestTemplate Request End");
        }
    }
}
