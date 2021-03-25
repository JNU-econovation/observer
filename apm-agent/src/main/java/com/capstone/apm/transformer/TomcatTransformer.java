package com.capstone.apm.transformer;

import com.capstone.apm.transaction.Transaction;
import com.capstone.apm.transaction.TransactionContext;
import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;

import static com.capstone.apm.transaction.TransactionContext.getTransactionContext;

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
        public static void enter(@Advice.AllArguments Object[] args) {
            HttpServletRequest servletRequest = (HttpServletRequest)args[0];
            String traceId = servletRequest.getHeader("Trace-Id");

            TransactionContext context = getTransactionContext();
            if(traceId != null)
                context.startTransaction(traceId);
            else
                context.startTransaction();
            context.saveRequest(servletRequest);
        }

        @Advice.OnMethodExit
        public static void exit() {
            System.out.println(getTransactionContext().getTransactionAsString());
            getTransactionContext().endTransaction();
        }
    }
}
