package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    private String email;

    private final Pattern pattern = Pattern.compile("^(.+)@(.+).com$");

    public Customer(String firstName, String lastName, String email) {

        if(!isValid(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer details: name: " + firstName + " " + lastName + ", email: " + email;
    }

    private boolean isValid(String email) {
        return pattern.matcher(email).matches();
    }
}
