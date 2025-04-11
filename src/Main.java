import controller.CustomerController;
import controller.ProductController;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import repository.Imp.*;
import service.CustomerService;

public class Main {
    public static void main(String[] args) throws SQLException {
        CustomerController customerController = new CustomerController();

SqlProductRepo sqlProductRepo = new SqlProductRepo();
//sqlProductRepo.showCategory();
Scanner sc = new Scanner(System.in);
        System.out.println("1.Customers\n2.Products\n3.order\n4.exit");
        int choice = sc.nextInt();
        switch (choice)
        {
            case 1:
            customerController.runMenu();
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
