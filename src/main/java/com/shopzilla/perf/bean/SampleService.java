package com.shopzilla.perf.bean;

/**
 * Created by IntelliJ IDEA.
 * User: spradhan
 * Date: 5/24/11
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
import com.shopzilla.perf.aspect.PerfTimed;
import org.springframework.stereotype.Component;

@Component(value = "sampleService")
//@PerfTimed
public class SampleService {

    @PerfTimed
    public void sampleServiceMethod1() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PerfTimed
    public void sampleServiceMethod2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}