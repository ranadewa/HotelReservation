package test;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import ui.*;

import java.util.Collection;

public class Driver {
    public static void main(String[] args) {


        MainMenu.Selection selection;

        System.out.println("Welcome to Hotel Reservation App");
        do {
            selection = MainMenu.instance.getSelection();
            parse(selection);

        } while (selection != MainMenu.Selection.EXIT);
    }

    private static void parse(MainMenu.Selection selection) {
        switch (selection) {
            case ERROR -> System.out.println("Please provide valid input");
            case CREATE_ACCOUNT -> createAccount();
            case RESERVE -> reserve();
            case ADMIN -> openAdminMenu();
            case SEE_RESERVATIONS -> showReservations();
        }
    }

    private static void createAccount() {
        AccountCreationForm.instance.create();
    }

    private static void reserve() {
        RoomReservationForm.instance.reserve();
    }

    private static void openAdminMenu() {
        AdminMenu.Selection selection;

        do {
            selection = AdminMenu.instance.getSelection();
            parse(selection);

        } while (selection != AdminMenu.Selection.BACK_TO_MAIN);
    }

    private static void showReservations() {
        System.out.println("Insert your email. Format: name@domain.com");
        String email = FormUtils.getEmail();
        Collection<Reservation> reservations = HotelResource.instance.getCustomerReservations(email);

        if (reservations == null){
            System.out.println("No reservations available for the user");
            return;
        }

        reservations.forEach(reservation -> System.out.println(reservation));
    }

    private static void parse(AdminMenu.Selection selection) {
        switch (selection) {
            case ERROR -> System.out.println("Please provide valid input");

            case SEE_ALL_CUSTOMERS -> {
                Collection<Customer> customers = AdminResource.instance.getAllCustomers();
                if (customers.isEmpty()) {
                    System.out.println("system doesn't have customers");
                    return;
                }
                customers.forEach(customer -> System.out.println(customer));
            }

            case SEE_ALL_RESERVATIONS ->
                    AdminResource.instance.displayAllReservations();
            case SEE_ALL_ROOMS -> {
                Collection<IRoom> rooms = AdminResource.instance.getAllRooms();
                if (rooms.isEmpty()) {
                    System.out.println("No rooms in the system");
                    return;
                }
                rooms.forEach(iRoom -> System.out.println(iRoom));
            }

            case ADD_ROOM ->
                    RoomCreationForm.instance.create();
            case BACK_TO_MAIN -> System.out.println("Back to main");
        }
    }
}
