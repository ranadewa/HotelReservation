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

        Collection<IRoom> rooms = HotelResource.instance.findARoom(checkIn, checkOut);

        if (rooms.isEmpty())
        {
            checkIn = moveByXdates(checkIn, 7);
            checkOut = moveByXdates(checkOut, 7);

            System.out.println("Rooms not available for original dates.\n Searching for alternative days");
            System.out.println("Updated Checkin: " + checkIn);
            System.out.println("Updated Checkout: " + checkOut);
        }

        rooms = HotelResource.instance.findARoom(checkIn, checkOut);

        if (rooms.isEmpty()) {
            System.out.println("No Rooms available for dates. Please change your dates");
            return;
        }

        rooms.forEach(iRoom -> System.out.println(iRoom));

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

        IRoom selectedRoom = getSelectedRoom(rooms);

        Reservation reservation = HotelResource.instance.bookARoom(email, selectedRoom, checkIn, checkOut);
        System.out.println(reservation);
    }

    private IRoom getSelectedRoom(Collection<IRoom> rooms) {
        IRoom selectedRoom;

        while (true) {
            System.out.println("What room would you like to reserve?");
            String roomNumber = FormUtils.getRoomNumber().toString();

            boolean inAvailableList = rooms.stream()
                    .filter((room) -> room.getRoomNumber().equals(roomNumber))
                    .count() == 1;
            if (!inAvailableList) {
                System.out.println("The room selected is not in available list for the given dates.");
                continue;
            }

            selectedRoom = HotelResource.instance.getRoom(roomNumber);
            break;
        }

        return selectedRoom;
    }

    private Date moveByXdates(Date current, int X) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.DATE, X);
        return cal.getTime();
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
