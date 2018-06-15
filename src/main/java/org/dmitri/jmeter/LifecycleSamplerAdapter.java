package org.dmitri.jmeter;

import org.dmitri.groovysampler.JGroovyContext;

public abstract class LifecycleSamplerAdapter implements LifecycleSampler{

    public void afterConstruct(JGroovyContext context) {
        
    }
    
    public void beforeTermination() {
        
    }
    
}
