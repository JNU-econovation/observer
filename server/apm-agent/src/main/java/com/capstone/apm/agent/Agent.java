package com.capstone.apm.agent;

import com.capstone.apm.collector.CollectorClient;
import com.capstone.apm.commons.event.EventConfiguration;
import com.capstone.apm.transaction.TransactionContext;
import com.capstone.apm.transaction.event.TransactionEventSubscriber;
import com.capstone.apm.transaction.websocket.ServerConfiguration;
import com.capstone.apm.transformer.TransformerChain;
import com.capstone.apm.transformer.TransformerFactory;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation inst){
        ServerConfiguration serverConfiguration = new ServerConfiguration("http://localhost:8082");
        CollectorClient.initialize(serverConfiguration);

        Runtime.getRuntime().addShutdownHook(new Thread(new GracefulShutdownHook()));

        TransformerChain transformerChain = new TransformerChain(new TransformerFactory());
        transformerChain.doTransformChain(new AgentBuilder.Default(), inst);
    }
}
