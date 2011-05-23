package com.shopzilla.perf.bean;

import java.util.Date;

import com.shopzilla.perf.aspect.Loggable;
import com.shopzilla.perf.logger.LogLevel;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component(value = "simpleBean")
public class SimpleBean {

	private Date dateProperty;

	private Integer integerProperty;

	private String stringProperty;

	@Loggable(value = LogLevel.TRACE)
	public Date getDateProperty() {
		return dateProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public void setDateProperty(final Date dateProperty) {
		this.dateProperty = dateProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public Integer getIntegerProperty() {
		return integerProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public void setIntegerProperty(final Integer integerProperty) {
		this.integerProperty = integerProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public String getStringProperty() {
		return stringProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public void setStringProperty(final String stringProperty) {
		this.stringProperty = stringProperty;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dateProperty", dateProperty)
				.append("integerProperty", integerProperty).append(
						"stringProperty", stringProperty).toString();
	}
}
