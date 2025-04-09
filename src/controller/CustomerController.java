package controller;

import model.Customer;
import repository.CustomerRepo;
import service.CustomerService;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {
    private CustomerService customerService;
    private Scanner sc = new Scanner(System.in);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void runMenu() throws SQLException {
        while (true) {
            System.out.println("1. Add Customer\n2. Update Customer\n3. Show All Customers\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    //update
                    break;
                case 3:
                    customerService.showCustomers();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer() throws SQLException {
        System.out.println("Enter customer name:");
        String name = sc.nextLine();
        System.out.println("Enter customer email:");
        String email = sc.nextLine();
        System.out.println("Enter customer phone number:");
        String phone = sc.nextLine();
        System.out.println("Enter customer address:");
        String address = sc.nextLine();
        System.out.println("Enter customer password:");
        String password = sc.nextLine();

        Customer customer = new Customer(name, email, phone, address, password);
        boolean result = customerService.registerCustomer(customer);
        System.out.println(result ? "Customer added successfully." : "Customer already exists.");
    }

   //update
}