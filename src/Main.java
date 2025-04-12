import controller.CustomerController;
import controller.ProductController;
import jdk.jfr.Category;
import model.Customer;
import controller.CustomerController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Product;
import repository.Imp.*;
import service.CustomerService;

public class Main {
    public static void main(String[] args) throws SQLException {

SqlProductRepo sqlProductRepo = new SqlProductRepo();


//
//
Scanner sc = new Scanner(System.in);
        System.out.println("1.Customers\n2.Products\n3.order\n4.exit");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
           new CustomerController().runMenu();
        case 2:
            //productMenu
new ProductController().runMenu();
            case 3:
                //orderMenu

           case 4:
               break ;
        }



    }
}
