package com.app.customer.service;

import com.app.customer.dao.CustomerDao;
import com.app.customer.vo.Customer;
import com.app.framework.exception.HexApplicationException;

import java.util.List;

public class CustomerImpl implements ICustomer {
	
	private CustomerDao customerDao;

	public void setCustomerDao ( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}
	
	public CustomerDao getCustomerDao () {
		return customerDao;
	}

	public void insert(Customer object) throws HexApplicationException {
	
		System.out.println("inside insert in Service ");			
		customerDao.insert(object);
		
	}

	public void delete(Customer object) throws HexApplicationException {
	
		System.out.println("inside delete in Service ");	
		customerDao.delete(object);
	
	}

	public void deleteAll(java.util.Collection entries) throws HexApplicationException {
	
		System.out.println("inside deleteAll in Service ");		
		customerDao.deleteAll(entries);
	
	}

	public void update(Customer object) throws HexApplicationException {
	
		System.out.println("inside update in Service ");	
		//CustomerDao loDao = (CustomerDao) BootStrapper
		//					.getService().getBean("CustomerDao");
		customerDao.update(object);
	
	}

	public Object select(Customer object) throws HexApplicationException {
	
		System.out.println("inside select in Service ");	
		return customerDao.select(object);
	
	}

	public List getAllCustomer() throws HexApplicationException {
	
		System.out.println("inside getAllCustomer in Service ");	
		return customerDao.getAllCustomer();
	
	}


	public Object getAllCustomer(int startRecord, int endRecord) throws HexApplicationException {
	
		System.out.println("inside getAllCustomer in Service ");	
		return customerDao.getAllCustomer(startRecord, endRecord);
	
	}

	public int getCustomerCount() throws HexApplicationException {
	
		System.out.println("inside getCustomerCount in Service ");	
		return customerDao.getCustomerCount();
	
	}


	public List search(String fieldValue, String columnName) throws HexApplicationException {

	System.out.println("Entering into service implementation : " + fieldValue + "***" +columnName );
				
		return customerDao.search(fieldValue, columnName);
	
	}


}