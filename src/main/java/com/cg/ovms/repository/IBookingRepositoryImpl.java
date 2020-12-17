package com.cg.ovms.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.ovms.entities.Booking;
import com.cg.ovms.entities.Customer;
import com.cg.ovms.entities.Vehicle;

public class IBookingRepositoryImpl implements IBookingRepository {

	private EntityManager entityManager;

	public IBookingRepositoryImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	public Booking addBooking(Booking booking) {
		//adding duplicate
		//TypedQuery<Booking> q =entityManager.createQuery("SELECT b FROM BOOKING b WHERE b..getBookingId= ", Booking.class);
		//q.setParameter()
		entityManager.persist(booking); 
		Booking booking1 = entityManager.find(Booking.class,booking); 
		return booking1;
	}

	public Booking cancelBooking(Booking b) {
		// TODO Auto-generated method stub
		 if(entityManager.contains(b)) { 
			 entityManager.remove(b); 
		} 
		 else {
			 entityManager.merge(b); 
		} 
		return b;
	}

	public Booking updateBooking(Booking b) {
		// TODO Auto-generated method stub
		if(b==null) { 
			entityManager.persist(b); 
		} 
		else { 
		  //Booking booking=entityManager.find(Booking.class,b); 
		  entityManager.merge(b); 
		 }
		 //not existing id is null 
		return b;
	}

	public Booking viewBooking(Booking b) {
		// TODO Auto-generated method stub
		Booking booking = entityManager.find(Booking.class, b);
		return booking;
	}

	public List<Booking> viewAllBooking(Customer customer) {
		// TODO Auto-generated method stub
		String queryStr = "SELECT booking FROM BOOKING booking WHERE booking.customer_id=:pCustId";
		TypedQuery<Booking> query = entityManager.createQuery(queryStr, Booking.class); 
		query.setParameter("pCustId", customer.getCustomerId());
		List<Booking> bookingList = query.getResultList(); 
		return bookingList;
	}

	public List<Booking> viewAllBookingByDate(LocalDate bookingDate) {
		// TODO Auto-generated method stub
		TypedQuery<Booking> q=entityManager.createQuery("SELECT b FROM BOOKING b where b.BOOKINGDATE=:bDate",Booking.class); 
		q.setParameter("bDate",bookingDate); 
		List<Booking> BookingListByDate=q.getResultList(); 
		return BookingListByDate;
	}

	public List<Booking> viewAllBookingByVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		String queryStr = "SELECT booking FROM BOOKING booking WHERE booking.vehicle_id=:pVehicleId";
		TypedQuery<Booking> query = entityManager.createQuery(queryStr, Booking.class); 
		query.setParameter("pVehicleId", vehicle.getVehicleId());
		List<Booking> bookingList = query.getResultList(); 
		return bookingList;
	}

}
