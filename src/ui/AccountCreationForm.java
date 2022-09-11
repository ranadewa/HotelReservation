package ui;

import api.HotelResource;
import java.util.Scanner;

public class AccountCreationForm {
    public static AccountCreationForm instance = new AccountCreationForm();

    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Email format: name@domain.com");
        String email = FormUtils.getEmail();
        System.out.println("First Name");
        String firstName = scanner.nextLine();
        System.out.println("Last Name");
        String lastName = scanner.nextLine();

        HotelResource.instance.createCustomer(email, firstName, lastName);
    }
}
