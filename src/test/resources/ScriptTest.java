import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.dmitri.groovysampler.JGroovyContext;
import org.dmitri.jmeter.LifecycleSamplerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groovy.transform.TypeChecked;

@TypeChecked
class MyTest extends LifecycleSamplerAdapter {
    
    private static final Logger log = LoggerFactory.getLogger("jmeter.groovy.runner");

    @Override
    public void afterConstruct(JGroovyContext context) {
        System.out.println("init");
    }

    @Override
    public SampleResult sample(JGroovyContext context, Entry entry) {
        SampleResult res = new SampleResult();
        boolean isOK = false;
        res.setSampleLabel("gRPC call");
        // Start timing
        res.sampleStart();
        try {

            // Do something here ...

            String response = Thread.currentThread().getName();
            Thread.sleep(50);

            // Set up the sample result details res.setSamplerData(data);
            res.setResponseData(response, null);
            res.setDataType(SampleResult.TEXT);

            res.setResponseCodeOK();
            res.setResponseMessage("OK");// $NON-NLS-1$
            isOK = true;
        } catch (Exception ex) {
            log.error("failed",ex);
            res.setResponseCode("500");// $NON-NLS-1$
            res.setResponseMessage(ex.toString());
        }
        res.sampleEnd(); // End timing

        res.setSuccessful(isOK);

        return res;
    }

}