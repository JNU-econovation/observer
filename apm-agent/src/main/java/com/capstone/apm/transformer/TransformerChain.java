package com.capstone.apm.transformer;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

public class TransformerChain {

    private List<Transformer> transformList;

    public TransformerChain() {
        this.transformList = new ArrayList<>();
        transformList.add(new TomcatTransformer());
    }

    public void doTransformChain(AgentBuilder.Default agentBuilder, Instrumentation inst) {
        Extendable extendable = null;

        if(transformList.size() > 0){
            extendable = transformList.get(0).doTransform(agentBuilder);
        }

        for(int i = 1; i < transformList.size(); i++){
            extendable = transformList.get(i).doTransform(extendable);
        }

        if(transformList.size() > 0) {
            extendable.installOn(inst);
        }
    }
}
