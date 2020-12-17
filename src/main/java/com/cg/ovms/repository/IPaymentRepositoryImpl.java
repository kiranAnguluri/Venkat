package com.cg.ovms.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.ovms.entities.Booking;
import com.cg.ovms.entities.Payment;
import com.cg.ovms.entities.Vehicle;

public class IPaymentRepositoryImpl implements IPaymentRepository {

	private EntityManager entityManager;
 
	public IPaymentRepositoryImpl() {
		entityManager = JPAUtil.getEntityManager(); 
	}
	
	public Booking addPayment(Payment payment) {
		// TODO Auto-generated method stub
		entityManager.persist(payment);
        return payment.getBooking();
	}

	public Booking cancelPayment(Payment p) {
		// TODO Auto-generated method stub
		entityManager.remove(p);
		return p.getBooking();
	}

	public Booking viewPayment(Booking b) {
		// TODO Auto-generated method stub
		b = entityManager.find(Booking.class, b);
		return b;
	}

	public List<Booking> viewAllPayments(Vehicle vehicle) {
		// TODO Auto-generated method stub
		String queryStr = "SELECT booking FROM BOOKING booking WHERE booking.vehicle_id=:pVehicleId";
		TypedQuery<Booking> query = entityManager.createQuery(queryStr, Booking.class);
		query.setParameter("pVehicleId", vehicle.getVehicleId());
		List<Booking> bookingList = query.getResultList();
		return bookingList;
	}

	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		double sum = 0;
		
		//Select total_cost from Booking where booking_id in (select booking_id from payment where payment_date between d1 and d2);
		String queryString = "SELECT booking.total_cost FROM BOOKING booking WHERE booking.booking_id IN "
				+ "(SELECT payment.booking_id from PAYMENT payment WHERE payment.payment_date BETWEEN :low and :high";
		TypedQuery<Integer> query = entityManager.createQuery(queryString, Integer.class);
		query.setParameter("low", d1);
		query.setParameter("high", d2);
		List<Integer> costList = query.getResultList();
		
		for(Integer itr: costList)
			sum = sum + itr;
		return sum;
	}

	public double calculateTotalPayment(Vehicle v) {				
		// TODO Auto-generated method stub
	/*	String queryStr = "SELECT vehicle FROM Payment  WHERE Payment.customer_id=:pCustId";
		TypedQuery<Booking> query = entityManager.createQuery(queryStr, Booking.class);
		query.setParameter("pCustId", Payment.getPaymentId());
		List<Booking> bookingList = query.getResultList();
		return bookingList;*/
		return 0;
	}

}
