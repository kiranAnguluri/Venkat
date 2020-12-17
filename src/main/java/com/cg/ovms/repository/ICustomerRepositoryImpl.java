package com.cg.ovms.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.ovms.entities.Customer;

public class ICustomerRepositoryImpl implements ICustomerRepository {

	private EntityManager entityManager;

	public ICustomerRepositoryImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	public Customer addCustomer(Customer customer) {
		entityManager.persist(customer);
		customer = entityManager.find(Customer.class, customer);
		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		boolean entryExists = entityManager.contains(customer);
		if(entryExists)
		{
			entityManager.remove(customer);
		}
		else 
		{
			return null;
		} 
		return customer;
	}

	public Customer viewCustomer(Customer customer) {							//doubt
		customer = entityManager.find(Customer.class, customer);
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		boolean entryExists = entityManager.contains(customer.getCustomerId());
		if(entryExists)
		{
			customer = entityManager.merge(customer);
		}
		else
		{
			return null;
			//entityManager.persist(customer);
			//customer = entityManager.find(Customer.class, customer);
		}
		return customer;
	}

	public List<Customer> viewAllCustomer(String vtype) {						//not complete
		// TODO Auto-generated method stub
		//SELECT * FROM CUSTOMER WHERE 
		String queryStr = "SELECT customer FROM CUSTOMER customer WHERE customer.address=:pVehicleType";
		TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
		query.setParameter("pVehicleType", vtype);
		List<Customer> customerList = query.getResultList();
		return customerList;
	}

	public List<Customer> viewAllCustomersByLocation(String location) {
		// TODO Auto-generated method stub
		String queryStr = "SELECT customer FROM CUSTOMER customer WHERE customer.address=:pLocation";
		TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
		query.setParameter("pLocation", location);
		List<Customer> customerList = query.getResultList();
		return customerList;
	}
}
