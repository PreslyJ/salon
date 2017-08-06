/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventryPro.util;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author chedhena
 */
public class HibernateBaseFactoryUtil {
    
    
   private static SessionFactory sessionFactory=buildSessionFactory(); 
    
    

   private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }

        return sessionFactory;
   }
   
   public static SessionFactory getSessionFactory(){
       return sessionFactory;
   }
   
   
   public  Session getSession(){

	   return  getSessionFactory().openSession();
	   
   }
   
 

}
