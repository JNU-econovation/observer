package com.capstone.apm.agent;

import com.capstone.apm.transformer.TomcatTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation inst){
        AgentBuilder.Default agentBuilder = new AgentBuilder.Default();
        AgentBuilder.Identified.Extendable extendable = new TomcatTransformer().doTransform(agentBuilder);
        extendable.installOn(inst);
    }
}
