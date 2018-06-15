package org.dmitri.groovysampler;

import org.dmitri.jmeter.LifecycleSampler;

import lombok.Data;

@Data
public class JGroovyContext {
    
    private String scriptBody;
    private LifecycleSampler sampler;
    

}
