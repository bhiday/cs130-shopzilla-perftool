package com.shopzilla.perf.web;

import com.shopzilla.perf.aspect.PerfTimed;
import com.shopzilla.perf.data.PerfData;
import com.shopzilla.perf.database.PerfDataManager;
import com.sun.xml.internal.ws.api.server.DocumentAddressResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/29/11
 * Time: 12:33 PM
 * To change this template use File | Settings | File Templates.
 */

//@Transactional
@RequestMapping("/perf")
@Controller
public class PerfController {
    private static final Log LOG = LogFactory.getLog(PerfController.class);

    //@Autowired
    private PerfDataManager perfDataManager;

    @Autowired
    public void setPerfDataManager(PerfDataManager perfDataManager) {
        this.perfDataManager = perfDataManager;
    }

    @PerfTimed
    @RequestMapping(value = "/starttime/{startTime}", method = RequestMethod.GET)
    //@RequestMapping(value = "/starttime", method = RequestMethod.GET)
    public @ResponseBody String getDataByStartTime(@PathVariable("startTime") Long startTime, Model uiModel) {

         Date startDate = new Date();
         startDate.setTime(startTime);

         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String startString = formatter.format(startDate);

         List result = perfDataManager.getPerfDataByStartDate(startString);

         return getXmlFromList(result);
    }

    @PerfTimed
    @RequestMapping(value = "/starttime/{startTime}/endtime/{endTime}", method = RequestMethod.GET)
    public @ResponseBody String getDataByStartTimeEndTime(@PathVariable("startTime") Long startTime, @PathVariable("endTime") Long endTime, Model uiModel) {

         Date startDate = new Date();
         startDate.setTime(startTime);
         Date endDate = new Date();
         endDate.setTime(endTime);

         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String startString = formatter.format(startDate);
         String endString = formatter.format(endDate);

         List result = perfDataManager.getPerfDataByStartDateEndDate(startString, endString);

         return getXmlFromList(result);
    }

    @PerfTimed
    @RequestMapping(value = "/toutt", method = RequestMethod.GET)
    public @ResponseBody String getUtt(Model uiModel) throws ParseException{

         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         Date now = formatter.parse("2011-05-29 12:20:02");
         Date end = formatter.parse("2011-05-29 13:10:13");

         return String.valueOf(now.getTime()) + "   " + String.valueOf(end.getTime());
    }

    private String getXmlFromList(List list) {
        String ENCODING = "ISO-8859-1";
        StringBuilder out = new StringBuilder();
        out.append("<?xml version=\"1.0\" encoding=\""+ENCODING+"\"?>\n");
        out.append("<!DOCTYPE USERS SYSTEM \"users.dtd\">\n");
        out.append("<results>\n");
        for (Object obj : list)
        {
            if (obj instanceof PerfData)
            {
                PerfData perfData = (PerfData) obj;
                out.append("\t<result>\n");

                out.append("\t\t<methodName>");
                out.append(perfData.getMethodName());
                out.append("</methodName>\n");

                out.append("\t\t<invokeTime>");
                out.append(perfData.getInvokeTime());
                out.append("</invokeTime>\n");

                out.append("\t\t<execTime>");
                out.append(perfData.getExecTime());
                out.append("</execTime>\n");

                out.append("\t</result>\n");
            }
        }

        out.append("</results>\n");
        return out.toString();
    }

}
