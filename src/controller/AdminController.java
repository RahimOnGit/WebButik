package controller;

import model.Admin;
import repository.Imp.SqlAdmin;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {
    CustomerController customerController = new CustomerController();
    ProductController productController = new ProductController();
    OrderController orderController = new OrderController();
    Admin admin = new Admin();






public void addAdmin() throws SQLException
{

    System.out.println("Register new admin");
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter admin username: ");
    admin.setUsername(sc.next());
    System.out.println("Enter admin password: ");
    admin.setPassword(sc.next());
    new SqlAdmin().insertAdmin(admin);

}

    public void adminMenu() throws SQLException {


    boolean isValid = login();
    if(isValid)
    {


        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to Admin page");


        System.out.println("1.Customers\n2.Products\n3.exit");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                customers();

            case 2:
                products();
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");

        }
    }
    else
    {
        System.out.println("Invalid login");
    }




    }

    public void products() throws SQLException {

        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Product Page");

    do {
        System.out.println("1.show all products \n2.search by name\n3.add product \n4.update product\n5.back\n\n6.exit");

        choice = sc.nextInt();

    switch (choice) {
        case 1 -> productController.showProducts();
        case 2 -> productController.searchByName();
        case 3 -> productController.addProduct();
        case 4 -> productController.updateProduct();
        case 5 -> adminMenu();
        case 6-> System.exit(0);
        default -> System.out.println("Invalid choice");
    }

    }while (choice != 6);
    }






    public boolean login() throws SQLException {
    Scanner sc = new Scanner(System.in);
        System.out.println("Log in as admin");
        System.out.println("enter your ID : ");
        int id;
        id = Integer.parseInt(sc.nextLine());


        System.out.println("enter your password : ");
        String password = sc.nextLine();

       boolean res = admin.validate(id,password);
       if(res) {
           System.out.println("valid Admin ");
           return true;
       }
       else {
           System.out.println("Invalid Admin ");
           return false;
       }

    }




    public void customers() throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1.view customers\n2.customer order history\n3.delete customer\n4.back");

choice = sc.nextInt();
            switch (choice) {
                case 1->
                    showCustomers();
                case 2->
                    customerOrderHistory(); //check if customer does not have an order
                case 3->
                    deleteCustomer();
            case 4-> adminMenu();
            case 5 -> System.exit(0);
            }
        }while(choice!=4);
    }



    public void showCustomers() throws SQLException
    {
        System.out.println("customers list");
        customerController.viewCustomers();
    }

     void deleteCustomer() throws SQLException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer ID : ");
        int id =  sc.nextInt();
        customerController.deleteCustomer(id);
    }

    void customerOrderHistory() throws SQLException
    {

        orderController.orderHistory();


    }


}
