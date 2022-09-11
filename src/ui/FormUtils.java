package ui;

import java.util.Scanner;
import java.util.regex.Pattern;

public class FormUtils {

    public static FormUtils instance = new FormUtils();
    public enum Choice {
        YES,
        NO
    }

    static Choice getChoice() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("No") || answer.equalsIgnoreCase("N")) {
                return Choice.NO;
            }

            if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Y")) {
                return Choice.YES;
            }

            System.out.println("Please type Yes or No.");
        }
    }

    public static String getEmail() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            Pattern pattern = Pattern.compile("^(.+)@(.+).com$");

            if (pattern.matcher(answer).matches())
                return answer;

            System.out.println("Please provide email in format: name@domain.com");
        }
    }

     static Integer getRoomNumber() {
        Integer roomNumber;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                roomNumber = scanner.nextInt();
                break;
            } catch (Exception ex) {
                System.out.println("Please enter a number");
            }
        }

        return roomNumber;
    }
}
