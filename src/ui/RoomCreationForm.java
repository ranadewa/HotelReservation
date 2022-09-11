package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomCreationForm {
    public static RoomCreationForm instance = new RoomCreationForm();

    public void create() {

        List<IRoom> rooms = new ArrayList<>();

        while (true) {

            System.out.println("Enter room number");
            Integer roomNumber = FormUtils.getRoomNumber();
            double price = getPrice();
            RoomType type = getRoomType();

            Room room = new Room(roomNumber.toString(), price, type);
            rooms.add(room);

            if(finishCreation()) {
                break;
            }
        }

        AdminResource.instance.addRoom(rooms);
    }

    private RoomType getRoomType() {
        int type = 0;

        while (true) {
            try {
                System.out.println("Enter room type: 1 for single, 2 for double");
                Scanner scanner = new Scanner(System.in);
                type = scanner.nextInt();

                if(type != 1 && type != 2) {
                    throw new IllegalArgumentException();
                }

                break;
            } catch (Exception ex) {
                System.out.println("Please enter 1 or 2");
            }
        }

        return RoomType.values()[type - 1];
    }

    private double getPrice() {
        double price = 0;

        while (true) {
            try {
                System.out.println("Enter price per night");
                Scanner scanner = new Scanner(System.in);
                price = scanner.nextDouble();
                break;
            } catch (Exception ex) {
                System.out.println("Please enter a number");
            }
        }

        return price;
    }

    private boolean finishCreation() {

        System.out.println("Would you like to add another room? Y/N");

        return FormUtils.getChoice() == FormUtils.Choice.YES ? false : true;
    }
}
