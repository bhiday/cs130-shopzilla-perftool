package com.shopzilla.perf.database;

import com.shopzilla.perf.data.PerfData;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/29/11
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PerfDataManager {

    public List getPerfDataByStartDate(String startDate);
    public List getPerfDataByStartDateEndDate(String startDate, String endDate);
    public void createAndStorePerfData(PerfData perfData);
}
