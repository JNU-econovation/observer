package com.capstone.apm.agent;

import com.capstone.apm.transformer.TransformerChain;
import com.capstone.apm.transformer.TransformerFactory;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation inst){
        TransformerChain transformerChain = new TransformerChain(new TransformerFactory());
        transformerChain.doTransformChain(new AgentBuilder.Default(), inst);
    }
}
