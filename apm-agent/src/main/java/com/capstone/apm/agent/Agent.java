package com.capstone.apm.agent;

import com.capstone.apm.transformer.TransformerChain;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation inst){
        TransformerChain transformerChain = new TransformerChain();
        transformerChain.doTransformChain(new AgentBuilder.Default(), inst);
    }
}
