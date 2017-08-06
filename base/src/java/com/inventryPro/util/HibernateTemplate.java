package com.inventryPro.util;  
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
*@author chedhena Jayasinghe
*common class for all the hibernate datasource connections

**/

public class HibernateTemplate extends HibernateBaseFactoryUtil{

    public  void saveAll(List<? extends Object> list) {
		if (list == null || list.isEmpty()) {
		    System.out.println("List null or empty, nothing saved!");
		    return;
		}
	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (Object entity : list)
		    session.saveOrUpdate(entity);
		tx.commit();
    }    
    
    public  void save(Object o) {
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            session.save(o);
            Transaction trans = session.getTransaction();
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
             session.close();
        }
    }
    
    public  List<Object> getAll(Class<? extends Object> dataClass, String whereClause) {
            String whereHQL = "";
            if (whereClause != null && whereClause.trim().length() > 0)
                whereHQL = " where " + whereClause;

            Session session = getSession();

            List<Object> list = session.createQuery(
                    "from " + dataClass.getSimpleName() + whereHQL).list();
            
            return list;
    }
    
    public  void delete(Class<? extends Object> dataClass, String whereClause) {
            String whereHQL = "";
            if (whereClause != null && whereClause.trim().length() > 0)
                whereHQL = " where " + whereClause;

            Session session = getSession();
            Transaction tx = session.beginTransaction();

            String q = "delete from " + dataClass.getSimpleName() + whereHQL;
            Query query = session.createQuery(q);
            query.executeUpdate();

            tx.commit();
    }
}