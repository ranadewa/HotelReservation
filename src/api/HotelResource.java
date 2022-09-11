package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static HotelResource instance = new HotelResource();

    public Customer getCustomer(String email) {
        return CustomerService.instance.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        CustomerService.instance.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return ReservationService.instance.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkIn, Date checkOut) {
        Customer customer = getCustomer(customerEmail);

        if (customer == null)
            return null;

        return ReservationService.instance.reserveARoom(customer, room, checkIn, checkOut);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);

        if (customer == null)
            return null;

        return  ReservationService.instance.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return ReservationService.instance.findRooms(checkIn, checkOut);
    }
}
