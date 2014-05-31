

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


public class VehicleDAO {
	
	static Session session = null;
	public static void main(String[] args)
	{
		// session = HibernateUtil.getSessionFactory().openSession();
		
		//session.beginTransaction();
		System.out.println( SearchForVehicleNum("AP13R5977"));
	}

	 public static void createAndStoreEvent(Vehicle vObj) {
	   	if(session==null && !session.isOpen())
		 session = HibernateUtil.getSessionFactory().openSession();//.getCurrentSession();
	   	else
	    	{
	    		session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	}
	    	session.beginTransaction();

	        session.save(vObj);

	        session.getTransaction().commit();
	        
	    }
	 
	 
	 public static boolean SearchForVehicleNum(String regdNumber)
	 {
		 if(session==null || !session.isOpen())
			 session = HibernateUtil.getSessionFactory().openSession();//.getCurrentSession();
		    	else
		    	{
		    		try{
		    		session = HibernateUtil.getSessionFactory().getCurrentSession();
		    	}catch(HibernateException he)
		    	{
		    		session = HibernateUtil.getSessionFactory().openSession();
		    	}
		    	}
		
		 session.beginTransaction();
		// System.out.println(session);
		 Query query=  session.createQuery("from Vehicle where RegistrationNumber = :regNum ");
		 query.setParameter("regNum",regdNumber );
		 java.util.List  list = query.list();
		 //session.getTransaction().commit();
		 session.getTransaction().rollback();
		 session.close();
		 return (list.size()>0);
		 
		 
	 }
}
