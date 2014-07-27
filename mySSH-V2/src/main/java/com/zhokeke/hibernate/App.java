package com.zhokeke.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
	public static ServiceRegistry serviceRegistry;
    public static void main( String[] args ) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
    {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    	//"hibernate.cfg.xml"
       Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
       //cfg.addResource("com/zhokeke/hibernate/base.hbm.xml");
       serviceRegistry  = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
      
       SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
       Base base = new Base();
       base.setName("zkk" + df.format(new Date()));
       
      
       Session session = sessionFactory.openSession();
       org.hibernate.Transaction tx = session.beginTransaction();
       session.save(base);
       tx.commit();
       
       session.close();
       sessionFactory.close();
       System.out.println("ok");
    }
}
