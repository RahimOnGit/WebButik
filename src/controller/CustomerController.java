package controller;

import model.Customer;
import repository.CustomerRepo;
import repository.Imp.SqlCustomerRep;
import service.CustomerService;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController extends SqlCustomerRep {

    private Scanner sc = new Scanner(System.in);
    CustomerService customerService =new CustomerService(new SqlCustomerRep());
    SqlCustomerRep sql = new SqlCustomerRep();

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
                    System.out.println("Enter Customer ID");
                    int customerId = sc.nextInt();
                    updateCustomer(customerId);
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


        Customer customer = customerEntry();
         customerService.registerCustomer(customer);

    }

    public void updateCustomer(int customerId) throws SQLException {
      Customer updatedCustomer = customerEntry();
         customerService.updateCustomer(customerId,updatedCustomer);
    }
   //update




private Customer customerEntry()
{
    Customer customer;
    sc.nextLine();
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

     customer = new Customer(name, email, phone, address, password);
return customer;
}
}