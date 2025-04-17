package controller;

import model.Customer;
import model.Order;
import repository.Imp.SqlCustomerRep;
import service.CustomerService;
import service.OrderService;
import service.SessionManagment;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController extends SqlCustomerRep {

    private Scanner sc = new Scanner(System.in);
    CustomerService customerService =new CustomerService(new SqlCustomerRep());
    SqlCustomerRep sql = new SqlCustomerRep();
    OrderService orderService = new OrderService();
    public void runMenu() throws SQLException {

    //   Customer customer =  signIn();


           Scanner sc = new Scanner(System.in);
           int choice=0;
        //   System.out.println("Welcome " + customer.getName());

        Customer customer = new Customer(6,"Semo Elhaj","Semo666@gmail.com","076363753","hisstornsgatan 8","123456");
       do{
           System.out.println("\n1.order history\n2.order\n3.update my info\n4.Log out\n5.Exit");

           choice = sc.nextInt();
           switch (choice) {
               case 1 -> customerOrderHistory(customer);
               case 2 ->
                       new CartController().cartMenu(customer);
               case 3 -> updateCustomer(customer.getId());
               case 4 -> { customer = signIn(); }
           }
       }  while (choice!=5);

        }

      public Customer signIn() throws SQLException {
          System.out.println("1. Login");
          System.out.println("2. Register");
          System.out.println("3. Exit");
          int choice;
          do {
               choice = sc.nextInt();
              Customer customer = null;
              switch (choice) {
                  case 1 -> {
                      customer = login();
                  }
                  case 2 -> register();
                  case 3 -> System.exit(0);
              }


              return customer;
          }while (choice != 3);
          }
        public Customer login() throws SQLException {
            System.out.println("Sign in\n");
            System.out.println("enter email :");
            String email = sc.next();
            System.out.println("enter password :");
            String password = sc.next();

            return new SessionManagment().login(email, password);

        }

        public void customerOrderHistory(Customer customer) throws SQLException {
            System.out.println(customer.getName()+" order history");
       List <Order> orders =  orderService.getOrderHistoryForCustomer(customer.getId());
           if(orders.isEmpty())
           {
               System.out.println(customer.getName()+" order history is empty");
           }
               for(Order order : orders)
               {
                   System.out.println(order.toString());

               }

        }
        public void register() throws SQLException {
            System.out.println("Registration");
    addCustomer();
    }
    public void addCustomer() throws SQLException {


        Customer customer = customerEntry();
         customerService.registerCustomer(customer);

    }

    public void updateCustomer(int customerId) throws SQLException {
        System.out.println("Update your information\n");
      Customer updatedCustomer = customerEntry();
         customerService.updateCustomer(customerId,updatedCustomer);
    }
   //update

public void viewCustomers() throws SQLException {
    List<Customer> customer = customerService.showCustomers();
    for (Customer c : customer) {
        System.out.println(c.toString());
    }
}


public Customer customerEntry()
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