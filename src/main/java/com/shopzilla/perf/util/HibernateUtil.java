package com.shopzilla.perf.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: adityabhiday
 * Date: 5/28/11
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

      private static SessionFactory buildSessionFactory() {
          try {
              // Create the SessionFactory from hibernate.cfg.xml
              return new Configuration().configure().buildSessionFactory();
          }
          catch (Throwable ex) {
              // Make sure you log the exception, as it might be swallowed
              System.err.println("Initial SessionFactory creation failed." + ex);
              throw new ExceptionInInitializerError(ex);
          }
      }

      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }
}
