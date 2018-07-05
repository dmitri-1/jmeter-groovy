package org.dmitri.groovysampler;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.dmitri.jmeter.LifecycleSampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groovy.lang.GroovyClassLoader;

public class JGroovySampler extends AbstractSampler implements TestBean {

    private static final long serialVersionUID = 240L;

    private static final Logger log = LoggerFactory.getLogger(JGroovySampler.class);

    // The name of the property used to hold our data
    public static final String DATA = JGroovySampler.class.getSimpleName() + ".data"; //$NON-NLS-1$

    private static AtomicInteger classCount = new AtomicInteger(0); // keep track of classes created

    // (for instructional purposes only!)
    private JGroovyContext context;

    public JGroovySampler() {

        int count = classCount.incrementAndGet();
        info("JGroovySampler:JGroovySampler() version 1.0.1 #" + count);
    }

    @Override
    public void setProperty(JMeterProperty property) {
        super.setProperty(property);
        if(property.getName().equals(DATA)) {
            if (context == null)
                context = buildContext();
            if (context != null)
                context.getSampler().afterConstruct(context);    
        }
            
    }

    @SuppressWarnings("unchecked")
    private JGroovyContext buildContext() {
        JGroovyContext groovyContext = new JGroovyContext();

        final GroovyClassLoader classLoader = new GroovyClassLoader(JGroovySampler.class.getClassLoader(), new CompilerConfiguration(), true);

        String script = getData();
        groovyContext.setScriptBody(script);
        Class<LifecycleSampler> parseClass = classLoader.parseClass(script);
        
        LifecycleSampler newInstance = null;

        try {
            newInstance = parseClass.getDeclaredConstructor().newInstance();
            classLoader.close();
        } catch (Exception e) {
            log.error("failed ",e);
            return null;
        }
        groovyContext.setSampler(newInstance);
        return groovyContext;
    }

    @Override
    public SampleResult sample(Entry entry) {
        trace("JGroovySampler:sample()");
        return context.getSampler().sample(context, entry);
    }

    /**
     * @return a string for the sampleResult Title
     */
    private String getTitle() {
        return this.getName();
    }

    /**
     * @return the data for the sample
     */
    public String getData() {
        String data = getPropertyAsString(DATA);
        return data;
    }

    /*
     * Helper method
     */
    private void trace(String s) {
        String tl = getTitle();
        String tn = Thread.currentThread().getName();
        String th = this.toString();
        log.debug(tn + " (" + classCount.get() + ") " + tl + " " + s + " " + th);
    }

    private void info(String s) {
        String tl = getTitle();
        String tn = Thread.currentThread().getName();
        String th = this.toString();
        log.info(tn + " (" + classCount.get() + ") " + tl + " " + s + " " + th);
    }

}
