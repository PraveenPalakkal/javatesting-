package com.app.customer.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.app.customer.util.HibernateUtil;
import com.app.customer.vo.Customer;
import com.app.framework.exception.HexApplicationException;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer select(Customer object) throws HexApplicationException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = (Customer)session.get(Customer.class, object.getCustomerid());
		transaction.commit();
		session.close();
		return customer;
	}

	@Override
	public void insert(Customer object) throws HexApplicationException {
		System.out.println("inside insert..");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(object);
			transaction.commit();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}

	}

	@Override
	public void update(Customer object) throws HexApplicationException {
		System.out.println("inside update..");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			transaction.commit();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}

	}

	@Override
	public void delete(Customer object) throws HexApplicationException {
		System.out.println("inside delete..");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}

	}

	@Override
	public void deleteAll(Collection entries) throws HexApplicationException {
		System.out.println("inside delete..");
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			for(Object object : entries)
				session.delete(object);
			transaction.commit();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}

	}

	@Override
	public List getAllCustomer() throws HexApplicationException {
		System.out.println("inside getAllCustomer..");
		List<Customer> customers = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			String hql = "From Customer";
			Query query = session.createQuery(hql);
			customers = query.list();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}
		return customers;
	}

	@Override
	public List getAllCustomer(int startRecord, int endRecord)
			throws HexApplicationException {
		return null;
	}

	@Override
	public int getCustomerCount() throws HexApplicationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List search(String fieldValue, String columnName)
			throws HexApplicationException {
		System.out.println("inside getAllCustomer..");
		List<Customer> customers = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			String hql = "From Customer where " + columnName + " like '%" + fieldValue + "%'	";
			Query query = session.createQuery(hql);
			customers = query.list();
			session.close();
			System.out.println("done...");
		} catch (Exception exception) {
			throw new HexApplicationException ( exception );
		}
		return customers;
	}
	
}