package com.capstone.apm.transformer;

import com.capstone.apm.transformer.interceptor.Interceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;
import net.bytebuddy.asm.Advice;

import static net.bytebuddy.matcher.ElementMatchers.named;

public abstract class AbstractTransformer implements Transformer{
    @Override
    public Extendable doTransform(AgentBuilder.Default agentBuilder) {
        return agentBuilder
                .type(named(getExpectedClassName()))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder
                                .method(named(getExpectedMethodName()))
                                .intercept(Advice.to(getInterceptor())));
    }

    @Override
    public Extendable doTransform(Extendable extendable) {
        return extendable
                .type(named(getExpectedClassName()))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder
                                .method(named(getExpectedMethodName()))
                                .intercept(Advice.to(getInterceptor())));
    }

    protected abstract String getExpectedMethodName();
    protected abstract String getExpectedClassName();
    protected abstract Class<? extends Interceptor> getInterceptor();
}
