package org.dmitri.groovysampler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jmeter.samplers.Entry;
import org.junit.jupiter.api.Test;

public class JGroovySamplerTest {

    @Test
    public void makeGroovyClass() throws Exception {
        JGroovySampler groovySampler = new JGroovySampler();
        groovySampler.setProperty(JGroovySampler.DATA, readTestGroovy());
        groovySampler.actionPerformed(null); //setup context
        Entry entry=null;
        groovySampler.sample(entry);
    }
    
    private String readTestGroovy() throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource("script-demo.groovy").toURI());
        return new String(Files.readAllBytes(path));
    }
}
