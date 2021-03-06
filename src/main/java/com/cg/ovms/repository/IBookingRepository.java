package com.cg.ovms.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.ovms.entities.Booking;
import com.cg.ovms.entities.Customer;
import com.cg.ovms.entities.Vehicle;

public interface IBookingRepository {

	public Booking addBooking(Booking booking);
	public Booking cancelBooking(Booking b);
	public Booking updateBooking(Booking b);
	public Booking viewBooking(Booking b);
	public List<Booking> viewAllBooking(Customer customer);
	public List<Booking> viewAllBookingByDate
	(LocalDate bookingDate);
	public List<Booking> viewAllBookingByVehicle(Vehicle vehicle);

}
