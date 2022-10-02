
package com.HostelMS.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.HostelMS.model.room;
import com.HostelMS.model.user;

public class HibernateUtil {

	private static SessionFactory sesionFact;                       //declaring session factory variable

	public static SessionFactory getSessionFactory() {

		if (sesionFact == null) {

			try {
				
				Configuration conf = new Configuration();             // configuration() method
				Properties properties = new Properties();             // properties of the Persistent class.
				
				properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/HostelDB");
				properties.put(Environment.PASS, "080197");
				properties.put(Environment.USER, "root");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(Environment.SHOW_SQL, "false");
				properties.put(Environment.HBM2DDL_AUTO, "update");
				properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				conf.setProperties(properties);
				conf.addAnnotatedClass(user.class);
				conf.addAnnotatedClass(room.class);

				
				sesionFact = conf.buildSessionFactory();           // session building
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return sesionFact;

	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}