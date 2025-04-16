import controller.*;
import jdk.jfr.Category;
import model.Customer;
import controller.CustomerController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Order;
import model.OrderProducts;
import model.Product;
import repository.Imp.*;
import repository.ProductRepo;
import service.CustomerService;
import service.SessionManagment;

public class Main {
    public static void main(String[] args) throws SQLException {

SqlProductRepo sqlProductRepo = new SqlProductRepo();
SqlOrderRepo sqlOrderRepo = new SqlOrderRepo();
AdminController adminController = new AdminController();
CustomerController customerController = new CustomerController();
Scanner scanner = new Scanner(System.in);
        System.out.println("1.Admin mode");
        System.out.println("2.Customer");
int choice = scanner.nextInt();
switch (choice) {
    case 1-> adminController.adminMenu();
    case 2-> customerController.runMenu();

}







////main
//Scanner sc = new Scanner(System.in);
//        System.out.println("1.Customers\n2.Products\n3.order\n4.exit");
//        int choice = sc.nextInt();
//        switch (choice)
//        {
//            case 1:
//           new CustomerController().runMenu();
//        case 2:
//            //productMenu
//new ProductController().runMenu();
//            case 3:
//                new OrderController().runMenu();
//
//           case 4:
//               break ;
//        }



    }

    public static void orderHistory()
    {
        System.out.println("enter customer_id");
        Scanner scanner = new Scanner(System.in);
        int customerId = scanner.nextInt();
       List<Order> orders =  new SqlOrderRepo().orderHistory(customerId);
       for (Order order : orders) {
           System.out.println(order.toString());
       }

    }

}

// Scanner scanner = new Scanner(System.in);
//        System.out.println("enter order id:");
//        int orderId = scanner.nextInt();
//
//        sqlProductRepo.getAllProducts();
//        System.out.println("enter product id:");
//        int productId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("enter quantity:");
//        int quantity = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("enter unit price:");
//        double unitPrice = scanner.nextDouble();