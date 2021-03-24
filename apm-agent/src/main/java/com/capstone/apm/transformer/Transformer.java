package com.capstone.apm.transformer;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;

public interface Transformer {
    Extendable doTransform(AgentBuilder.Default agentBuilder);
    Extendable doTransform(Extendable extendable);
}
