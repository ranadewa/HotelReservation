package ui;

import java.util.Scanner;

public class AdminMenu {

    public enum Selection {
        ERROR,
        SEE_ALL_CUSTOMERS,
        SEE_ALL_ROOMS,
        SEE_ALL_RESERVATIONS,
        ADD_ROOM,
        BACK_TO_MAIN
    }
    public static AdminMenu instance = new AdminMenu();

    public void display() {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
    }

    public Selection getSelection() {
        display();
        return parseInput();
    }

    private Selection parseInput() {
        Selection selection;
        try {
            Scanner scanner = new Scanner(System.in);
            int out = scanner.nextInt();
            selection = Selection.values()[out];
        } catch (Exception ex)
        {
            selection = Selection.ERROR;
        }
        return selection;
    }
}
