package services;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    public static CustomerService instance = new CustomerService();
    private Map<String, Customer> customerMap = new HashMap<>();

    public void addCustomer(String email, String firstName, String lastName) {

        if(customerMap.containsKey(email)){
            System.out.println("A user is already tied to this mail address");
            return;
        }

        try {
            Customer customer = new Customer(firstName, lastName, email);
            customerMap.put(email,customer);
        }
        catch (IllegalArgumentException exception)
        {
            System.out.println("Please provide valid email id");
        }
    }

    public Customer getCustomer(String customerEmail){

        if(customerMap.containsKey(customerEmail)) {
            return customerMap.get(customerEmail);
        }
        else {
            System.out.println("No customer found for given email");
            return null;
        }
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }
}
