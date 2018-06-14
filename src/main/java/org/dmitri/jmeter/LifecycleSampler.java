package org.dmitri.jmeter;

import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public interface LifecycleSampler {
    
    public void afterConstruct();
    
    public SampleResult sample(Entry entry);
    
    public void beforeTermination();

}
