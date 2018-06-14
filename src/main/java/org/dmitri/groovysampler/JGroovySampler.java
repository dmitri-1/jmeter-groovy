package org.dmitri.groovysampler;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.codehaus.groovy.tools.GroovyClass;
import org.dmitri.jmeter.LifecycleSampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JGroovySampler extends AbstractSampler  implements TestBean,LifecycleSampler{

    private static final long serialVersionUID = 240L;

    private static final Logger log = LoggerFactory.getLogger(JGroovySampler.class);

    // The name of the property used to hold our data
    public static final String DATA = JGroovySampler.class.getSimpleName()+ ".data"; //$NON-NLS-1$

    private static AtomicInteger classCount = new AtomicInteger(0); // keep track of classes created

    // (for instructional purposes only!)
    private static GroovyClass groovyClass;
    
    

    public JGroovySampler() {
        
        int count = classCount.incrementAndGet();
        if(count == 1)
            afterConstruct();
        info("JGroovySampler:JGroovySampler() version 1.0.1");
    }

    
    @Override
    public void afterConstruct() {
        trace("JGroovySampler:afterConstruct()");
        //final GroovyClassLoader classLoader = new GroovyClassLoader(Beans.class.getClassLoader(),
        //        new CompilerConfiguration(), true);
        //final Class<Predicate> clz = classLoader.parseClass(script
    }

    @Override
    public SampleResult sample(Entry entry) {
        
        log.info("get data "+getData());
        trace("JGroovySampler:sample()");
        
        SampleResult res = new SampleResult();
        
        res.setSampleLabel(getTitle());
        res.setSamplerData(getData());
        res.setResponseData("", null);
        res.setDataType(SampleResult.TEXT);
        
        res.sampleStart(); // Start timing
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
        res.sampleEnd(); // End timing

        res.setResponseCodeOK();
        res.setResponseMessage("OK");// $NON-NLS-1$
        res.setSuccessful(true);        
        
        return res;
    }

    @Override
    public void beforeTermination() {
        trace("JGroovySampler:beforeTermination()"); 
    }    
    
    /**
     * {@inheritDoc}
     */
    /*
    @Override
    public SampleResult sample(Entry e) {
        trace("sample()");
        SampleResult res = new SampleResult();
        boolean isOK = false; // Did sample succeed?
        String data = getData(); // Sampler data
        String response = null;

        res.setSampleLabel(getTitle());
         // Perform the sampling
        res.sampleStart(); // Start timing
        try {

            // Do something here ...

            response = Thread.currentThread().getName();

             //Set up the sample result details
            res.setSamplerData(data);
            res.setResponseData(response, null);
            res.setDataType(SampleResult.TEXT);

            res.setResponseCodeOK();
            res.setResponseMessage("OK");// $NON-NLS-1$
            isOK = true;
        } catch (Exception ex) {
            log.debug("", ex);
            res.setResponseCode("500");// $NON-NLS-1$
            res.setResponseMessage(ex.toString());
        }
        res.sampleEnd(); // End timing

        res.setSuccessful(isOK);

        return res;
    }
*/

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


    @Override
    public void setProperty(JMeterProperty property) {
        // TODO Auto-generated method stub
        super.setProperty(property);
        info("property "+property);
    }
    
    
}
