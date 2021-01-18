package com.cg.ebug.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import com.cg.ebug.entity.Customer_Table;
//import com.Ebug.entity.Customer_Ticket;
import com.cg.ebug.entity.Ticket_Table;
//import com.Ebug.entity.Customer_Ticket;
import com.cg.ebug.exception.EbugException;



public class Customer_Repository implements Customer_Repository_I{
	EntityManager manager = null;
	EntityTransaction transaction = null;
	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public boolean register(Customer_Table customer) {
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select c from Customer_Table c";
			TypedQuery<Customer_Table> query = manager.createQuery(jpql,Customer_Table.class);
			List<Customer_Table> list = query.getResultList();
			for(Customer_Table isExists : list) {
				if(isExists.getEmailId().equalsIgnoreCase(customer.getEmailId())) {
					throw new EbugException("Customer already Exists");
				}
			}
			manager.persist(customer);
			transaction.commit();
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Customer_Table login(String email, String password) {
		try {
			Customer_Table bean = getUser(email);
			if(bean.getEmailId()!= null && bean.getEmailId().equals(email) && bean.getPassword().equals(password)) {
				return bean;
			}else {
				return null;
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}
	}

	public Customer_Table getUser(String email) {
		manager=factory.createEntityManager();
		String jpql="select c from Customer_Table c where c.email=:email";
		TypedQuery<Customer_Table> query = manager.createQuery(jpql,Customer_Table.class);
		query.setParameter("email", email);
		Customer_Table bean = query.getSingleResult();
		return bean;
	}


	@Override
	public boolean sendTicketDetails(Ticket_Table ticket) {
		manager= factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();
		Ticket_Table ticketTable = new Ticket_Table();
		ticketTable.setId(ticket.getCustId());
		ticketTable.setComments(ticket.getComments());
		ticketTable.setFileName(ticket.getFileName());
		transaction.commit();
		return false;
	}

}