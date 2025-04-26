import controller.*;
import controller.CustomerController;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
AdminController adminController = new AdminController();
CustomerController customerController = new CustomerController();
Scanner scanner = new Scanner(System.in);
        System.out.println("1.Admin mode");
        System.out.println("2.Customer");
int choice = scanner.nextInt();
switch (choice) {
    case 1-> adminController.adminMenu();
    case 2-> customerController.runMenu();
    default -> System.out.println("Invalid choice");
}

    }
}
