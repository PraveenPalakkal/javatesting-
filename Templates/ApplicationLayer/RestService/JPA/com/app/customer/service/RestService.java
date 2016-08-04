package com.app.customer.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.customer.dao.CustomerDao;
import com.app.customer.util.BootStrapper;
import com.app.customer.util.GlobalConstants;
import com.app.customer.util.HexHelper;
import com.app.customer.vo.Customer;
import com.app.framework.QueryResultType;
import com.app.framework.exception.HexApplicationException;

@Path(value = "/ws")
public class RestService implements ICustomer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6247827984530466306L;
	CustomerDao customerDao;

	public RestService() {
		customerDao = (CustomerDao) BootStrapper.getService().getBean("CustomerDao");
	}

	@POST
	@Path(value = "/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public String insert(Customer customer) {
		try {
			System.out.println("inside insert service method..");
			customerDao.createEntityManager();
			String result = customerDao.insert(customer);
			customerDao.closeEntityManager();
			return result;
		} catch (HexApplicationException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@GET
	@Path(value = "/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Customer> getAllCustomer() throws HexApplicationException {
		System.out.println("inside getAllObject service method..");
		try {
			customerDao.createEntityManager();
			List<Customer> result = customerDao.getAllObjects(GlobalConstants.entityName);
			customerDao.closeEntityManager();
			System.out.println("list-->" + result);
			return result;
		} catch (HexApplicationException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HexApplicationException(e);
		}
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public String deleteAll(List<Customer> customerList) {
		System.out.println("inside deleteAll service method..");
		try {
			customerDao.createEntityManager();
			String result = customerDao.deleteAll(customerList);
			customerDao.closeEntityManager();
			return result;
		} catch (HexApplicationException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@POST
	@Path(value = "/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public String update(Customer customer){
		System.out.println("Inside updateCustomer method");
		try {
			customerDao.createEntityManager();
			String result = customerDao.update(customer);
			customerDao.closeEntityManager();
			return result;
		} catch (HexApplicationException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GET
	@Path(value = "/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Customer> search(@QueryParam("searchValue") String searchValue, @QueryParam("searchColumn") String searchColumn) throws HexApplicationException{
		System.out.println("inside searchCustomer method");
		try {
			if(searchValue == null || searchColumn == null)
				throw new Exception("Input cannot be null");
			customerDao.createEntityManager();
			List<Customer> result = customerDao.executeQuery(HexHelper.createCustomerQuerySearchCriteria(
					searchValue, searchColumn), QueryResultType.LIST);;
			customerDao.closeEntityManager();
			return result;
		} catch (HexApplicationException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HexApplicationException(e);
		}
		
	}
}
