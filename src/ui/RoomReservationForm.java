package ui;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class RoomReservationForm {
    public static RoomReservationForm instance = new RoomReservationForm();

    public void reserve() {

        System.out.println("Enter check-in Date mm/dd/yyyy");
        Date checkIn = getDate();

        System.out.println("Enter check-out Date mm/dd/yyyy");
        Date checkOut = getDate();

        if (checkOut.before(checkIn)){
            System.out.println("Check in date should be before checkout date. Please reselect");
            return;
        }

        boolean hasRooms = queryDates(checkIn, checkOut);

        if (!hasRooms) {
            System.out.println("No Rooms available for dates. Please change your dates");
            return;
        }

        System.out.println("Would you like to book a room? y/n");
        FormUtils.Choice choice = FormUtils.getChoice();

        if (choice == FormUtils.Choice.NO)
            return;

        System.out.println("Do you have an account with us? y/n");
        FormUtils.Choice accountChoice = FormUtils.getChoice();

        if (accountChoice == FormUtils.Choice.NO) {
            System.out.println("Please first create a user account before booking");
            return;
        }

        System.out.println("Enter email format: name@domain.com");
        String email = FormUtils.getEmail();

        Customer customer = HotelResource.instance.getCustomer(email);

        if (customer == null){
            System.out.println("Given email doesn't have an account in the system.\nPlease create account first");
            return;
        }

        System.out.println("What room would you like to reserve?");
        String roomNumber = FormUtils.getRoomNumber().toString();

        IRoom room = HotelResource.instance.getRoom(roomNumber);
        if (room == null) {
            System.out.println("Given room number doesn't exist. Returning to main");
            return;
        }

        Reservation reservation = HotelResource.instance.bookARoom(email, room, checkIn, checkOut);
        System.out.println(reservation);
    }

    private boolean queryDates(Date checkIn, Date checkOut) {
        Collection<IRoom> rooms = HotelResource.instance.findARoom(checkIn, checkOut);

        if (rooms.isEmpty())
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkIn);
            cal.add(Calendar.DATE, 7);
            Date updatedCheckIn = cal.getTime();

            cal.setTime(checkOut);
            cal.add(Calendar.DATE, 7);
            Date updatedCheckout = cal.getTime();

            System.out.println("Rooms not available for original dates.\n Searching for alternative days");
            System.out.println("Updated Checkin: " + updatedCheckIn.toString());
            System.out.println("Updated Checkout: " + updatedCheckout.toString());
            rooms = HotelResource.instance.findARoom(updatedCheckIn, updatedCheckout);
        }

        rooms.forEach(iRoom -> System.out.println(iRoom));

        return !rooms.isEmpty();
    }

    private Date getDate() {
        Date date;
        while (true) {

            try {
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                date = formatter.parse(str);
                break;
            } catch (Exception ex) {
                System.out.println("Please provide a date in mm/dd/yyyy format");
            }

        }

        return  date;
    }

}
