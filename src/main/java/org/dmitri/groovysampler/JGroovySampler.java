package org.dmitri.groovysampler;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.dmitri.jmeter.LifecycleSampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JGroovySampler extends AbstractSampler  implements TestBean,LifecycleSampler{

    private static final long serialVersionUID = 240L;

    private static final Logger log = LoggerFactory.getLogger(JGroovySampler.class);

    // The name of the property used to hold our data
    public static final String DATA = "grovy.sampler.data"; //$NON-NLS-1$

    private static AtomicInteger classCount = new AtomicInteger(0); // keep track of classes created

    // (for instructional purposes only!)

    public JGroovySampler() {
        classCount.incrementAndGet();
        trace("JGroovySampler:JGroovySampler()");
    }

    
    @Override
    public void afterConstruct() {
        trace("JGroovySampler:afterConstruct()");
    }

    @Override
    public SampleResult sample(Entry entry) {
        trace("JGroovySampler:sample()");
        return null;
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
        return getPropertyAsString(DATA);
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
}
