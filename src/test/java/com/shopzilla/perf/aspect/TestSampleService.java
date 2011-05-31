package com.shopzilla.perf.aspect;

/**
 * Created by IntelliJ IDEA.
 * User: spradhan
 * Date: 5/24/11
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */
import com.shopzilla.perf.bean.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext/applicationContext-aspect.xml",
		"/applicationContext/applicationContext-logger.xml",
		"/applicationContext/applicationContext.xml",
        "/META-INF/spring/integration-data.xml"})
public class TestSampleService {

    @Autowired
    @Qualifier(value = "sampleService")
    private SampleService sampleService;

    @Test
    public final void testSampleServiceMethod1() {
        sampleService.sampleServiceMethod1();
    }

    @Test
    public final void testSampleServiceMethod2() {
        sampleService.sampleServiceMethod2();
    }

}