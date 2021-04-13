package com.capstone.apm.transformer;

import java.util.ArrayList;
import java.util.List;

public class TransformerFactory {
    public List<Transformer> getTransformers() {
        List<Transformer> transformers = new ArrayList<>();
        transformers.add(new TomcatTransformer());
        transformers.add(new RestTemplateTransformer());
        return transformers;
    }
}
