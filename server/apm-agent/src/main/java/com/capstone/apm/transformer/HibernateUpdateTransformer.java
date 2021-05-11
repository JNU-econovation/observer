package com.capstone.apm.transformer;

import com.capstone.apm.transformer.interceptor.Interceptor;

// executeUpdate method is used when Hibernate execute insert, update and delete query
public class HibernateUpdateTransformer extends AbstractTransformer{
    
    private final String EXPECTED_CLASS_NAME = "com.mysql.cj.jdbc.ClientPreparedStatement";
    
    private final String EXPECTED_METHOD_NAME = "executeUpdate";
    
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
        return HibernateSelectTransformer.MySqlInterceptor.class;
    }
}
