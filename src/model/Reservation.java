package model;

import java.util.Date;

public class Reservation {
    final private Customer customer;
    final private IRoom room;
    final private Date checkInDate;
    final private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation details: Customer: " + customer.toString() + ", Room: " + room.toString() +
                ", Check in: " + checkInDate.toString() + ", Check out: " + checkOutDate.toString();
    }
}
