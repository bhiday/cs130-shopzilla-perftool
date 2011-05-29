package com.shopzilla.perf.database;

import com.shopzilla.perf.data.PerfData;
import com.shopzilla.perf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.loader.custom.CustomLoader;
import sun.security.jca.GetInstance;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/28/11
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PerfDataManager {

    private static PerfDataManager _sInstance;

    private PerfDataManager() {
    }

    public static PerfDataManager getInstance() {
        if (_sInstance == null) _sInstance = new PerfDataManager();
        return _sInstance;
    }

    /*public static void main(String[] args) {
          PerfDataManager mgr = new PerfDataManager();

          if (args[0].equals("store")) {
              mgr.createAndStorePerfData("METHODNAME", new Date(), 12345L);
          }

          HibernateUtil.getSessionFactory().close();
      }
    */

      public void createAndStorePerfData(String methodName, Date invokeTime, Long execTime) {
          Session session = HibernateUtil.getSessionFactory().getCurrentSession();
          session.beginTransaction();

          PerfData perfData = new PerfData();
          perfData.setMethodName(methodName);
          perfData.setInvokeTime(invokeTime);
          perfData.setExecTime(execTime);
          session.save(perfData);

          session.getTransaction().commit();
      }

      public List getPerfDataByStartDate(String startDate) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("FROM " + PerfData.class.getName() + " WHERE " +
                "invokeTime > '" + startDate + "'").list();

        session.getTransaction().commit();

        return result;

      }

      public List <PerfData> getPerfDataByStartDateEndDate(String startDate, String endDate) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("FROM " + PerfData.class.getName() + " WHERE " +
                "invokeTime > '" + startDate + "' AND invokeTime < '" + endDate + "'").list();

        session.getTransaction().commit();

        return result;

      }

}
