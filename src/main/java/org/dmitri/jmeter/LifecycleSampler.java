package org.dmitri.jmeter;

import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.dmitri.groovysampler.JGroovyContext;

public interface LifecycleSampler {
    
    public void afterConstruct(JGroovyContext context);
    
    public SampleResult sample(JGroovyContext context, Entry entry);
    
    public void beforeTermination();

    

}
