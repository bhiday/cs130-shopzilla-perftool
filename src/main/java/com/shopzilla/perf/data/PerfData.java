package com.shopzilla.perf.data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/28/11
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PerfData {

    private Long id;
    private String methodName;
    private Date invokeTime;
    private Long execTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Date getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(Date invokeTime) {
        this.invokeTime = invokeTime;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }
}
