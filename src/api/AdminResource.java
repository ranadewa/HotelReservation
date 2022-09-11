package api;

import model.Customer;
import model.IRoom;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public static AdminResource instance = new AdminResource();

    public Customer getCustomer(String email) {
        return CustomerService.instance.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach((IRoom room) -> ReservationService.instance.addRoom(room));
    }

    public Collection<IRoom> getAllRooms() {
        return ReservationService.instance.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return CustomerService.instance.getAllCustomers();
    }

    public void displayAllReservations() {
        ReservationService.instance.printAllReservation();
    }
}
