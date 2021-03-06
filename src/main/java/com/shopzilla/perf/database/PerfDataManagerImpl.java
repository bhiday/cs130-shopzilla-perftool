package com.shopzilla.perf.database;

import com.shopzilla.perf.data.PerfData;
import com.shopzilla.perf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.CustomLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/28/11
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository("perfDataManager")
@Transactional
public class PerfDataManagerImpl implements PerfDataManager{

  //@Autowired
  private SessionFactory sessionFactory;

  @Autowired
  public PerfDataManagerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public void createAndStorePerfData(PerfData perfData) {
          sessionFactory.getCurrentSession().save(perfData);
      }

    //@Override
  @SuppressWarnings("unchecked")
  @Transactional
    public List getPerfDataByStartDate(String startDate) {
            return sessionFactory.getCurrentSession().createQuery("FROM " + PerfData.class.getName() + " WHERE " +
                    "invokeTime > '" + startDate + "'").list();
    }

  @SuppressWarnings("unchecked")
  @Transactional
  public List getPerfDataByStartDateEndDate(String startDate, String endDate) {
    return sessionFactory.getCurrentSession().createQuery("FROM " + PerfData.class.getName() + " WHERE " +
            "invokeTime > '" + startDate + "' AND invokeTime < '" + endDate + "'").list();

}

    /*
    private static PerfDataManager _sInstance;

    private PerfDataManagerImpl() {
    }

    public static PerfDataManager getInstance() {
        if (_sInstance == null) _sInstance = new PerfDataManagerImpl();
        return _sInstance;
    }

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

      public List getPerfDataByStartDateEndDate(String startDate, String endDate) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("FROM " + PerfData.class.getName() + " WHERE " +
                "invokeTime > '" + startDate + "' AND invokeTime < '" + endDate + "'").list();

        session.getTransaction().commit();

        return result;

      }*/

}
