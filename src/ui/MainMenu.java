package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    public enum Selection {
        ERROR,
        RESERVE,
        SEE_RESERVATIONS,
        CREATE_ACCOUNT,
        ADMIN,
        EXIT
    }
    public static MainMenu instance = new MainMenu();

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

    private void display() {
        delimiter();
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        delimiter();
        System.out.println("Please select a number for the menu option");
    }

    private void delimiter() {
        System.out.println("-----------------------------------");
    }
}
