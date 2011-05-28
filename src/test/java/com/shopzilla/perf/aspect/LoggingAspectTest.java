package com.shopzilla.perf.aspect;

import java.math.BigDecimal;

import junit.framework.Assert;
import com.shopzilla.perf.bean.SimpleBean;
import com.shopzilla.perf.bean.SimpleBeanSubclass;
import com.shopzilla.perf.logger.LogLevel;
import com.shopzilla.perf.logger.MockLogger;
import com.shopzilla.perf.logger.MockLogger.LogMessage;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/applicationContext/applicationContext-aspect.xml",
		"/applicationContext/applicationContext-logger.xml",
		"/applicationContext/applicationContext.xml" })
public class LoggingAspectTest {

	@Autowired
	private MockLogger logger;
	
	@Autowired
	@Qualifier(value = "simpleBean")
	public SimpleBean simpleBean;

	@Autowired
	public SimpleBeanSubclass simpleBeanSubclass;

	@Before
	public void before() {
		logger.setLogLevel(SimpleBean.class, LogLevel.TRACE);
		logger.setLogLevel(SimpleBeanSubclass.class, LogLevel.TRACE);
		logger.resetLoggers();
	}
	
	@Test
	public void testSimpleBean_SetDateProperty() throws Exception {
		simpleBean.setDateProperty(
				DateUtils.parseDate("01/01/2010", new String[] {"dd/MM/yyyy"}));
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < setDateProperty > with params Fri Jan 01 00:00:00 PST 2010 ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setDateProperty > ]");
	}
	
	@Test
	public void testSimpleBean_SetIntegerProperty() {
		simpleBean.setIntegerProperty(100);
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < setIntegerProperty > with params 100 ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setIntegerProperty > ]");
	}
	
	@Test
	public void testSimpleBean_SetStringProperty() {
		simpleBean.setStringProperty("stringProperty");
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < setStringProperty > with params stringProperty ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setStringProperty > ]");
	}
	
	@Test
	public void testSimpleBean_GetDateProperty() {
		simpleBean.getDateProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < getDateProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getDateProperty > returning Fri Jan 01 00:00:00 PST 2010 ]");
	}
	
	@Test
	public void testSimpleBean_GetIntegerProperty() {
		simpleBean.getIntegerProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < getIntegerProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getIntegerProperty > returning 100 ]");
	}
	
	@Test
	public void testSimpleBean_GetStringProperty() {
		simpleBean.getStringProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0),
				LogLevel.TRACE,
				"[ entering < getStringProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getStringProperty > returning stringProperty ]");
	}

	@Test
	public void testSimpleBeanSubclass_SetDateProperty() throws Exception {
		simpleBeanSubclass.setDateProperty(
				DateUtils.parseDate("01/01/2010", new String[] {"dd/MM/yyyy"}));
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < setDateProperty > with params Fri Jan 01 00:00:00 PST 2010 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setDateProperty > ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_SetDecimalProperty() {
		simpleBeanSubclass.setDecimalProperty(new BigDecimal("0.25"));
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < setDecimalProperty > with params 0.25 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setDecimalProperty > ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_SetIntegerProperty() {
		simpleBeanSubclass.setIntegerProperty(100);
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < setIntegerProperty > with params 100 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setIntegerProperty > ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_SetStringProperty() {
		simpleBeanSubclass.setStringProperty("stringProperty");
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < setStringProperty > with params stringProperty ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < setStringProperty > ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_GetDateProperty() {
		simpleBeanSubclass.getDateProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < getDateProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getDateProperty > returning Fri Jan 01 00:00:00 PST 2010 ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_GetDecimalProperty() {
		simpleBeanSubclass.getDecimalProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < getDecimalProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getDecimalProperty > returning 0.25 ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_GetIntegerProperty() {
		simpleBeanSubclass.getIntegerProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < getIntegerProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getIntegerProperty > returning 100 ]");
	}
	
	@Test
	public void testSimpleBeanSubclass_GetStringProperty() {
		simpleBeanSubclass.getStringProperty();
		
		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0),
				LogLevel.TRACE,
				"[ entering < getStringProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1),
				LogLevel.TRACE,
				"[ leaving < getStringProperty > returning stringProperty ]");
	}
	
	private void assertEquals(LogMessage logMessage, LogLevel logLevel, String message) {
		Assert.assertEquals(logLevel, logMessage.getLogLevel());
		
		Assert.assertEquals(message, logMessage.getMessage());		
	}
}
