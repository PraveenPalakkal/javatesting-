package com.app.customer.service;

import com.app.customer.dao.CustomerDao;
import com.app.customer.vo.Customer;
import com.app.framework.exception.HexApplicationException;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public interface ICustomer extends Serializable {
	
	public void setCustomerDao ( CustomerDao customerDao );
	
	public CustomerDao getCustomerDao ();

	public void insert(Customer object) throws HexApplicationException;

	public void delete(Customer object) throws HexApplicationException;

	public void deleteAll(java.util.Collection entries) throws HexApplicationException;

	public void update(Customer object) throws HexApplicationException;

	public Object select(Customer object) throws HexApplicationException;

	public List getAllCustomer() throws HexApplicationException;

	public Object getAllCustomer(int startRecord, int endRecord) throws HexApplicationException;

	public int getCustomerCount() throws HexApplicationException;

	public List search(String fieldValue, String columnName) throws HexApplicationException;

	
}