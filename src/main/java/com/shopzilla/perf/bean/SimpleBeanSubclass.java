package com.shopzilla.perf.bean;

import java.math.BigDecimal;

import com.shopzilla.perf.aspect.Loggable;
import com.shopzilla.perf.logger.LogLevel;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component(value = "simpleBeanSubclass")
public class SimpleBeanSubclass extends SimpleBean {

	private BigDecimal decimalProperty;

	@Loggable(value = LogLevel.TRACE)
	public BigDecimal getDecimalProperty() {
		return decimalProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public void setDecimalProperty(final BigDecimal decimalProperty) {
		this.decimalProperty = decimalProperty;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("decimalProperty",
				decimalProperty).appendSuper(super.toString()).toString();
	}
}
