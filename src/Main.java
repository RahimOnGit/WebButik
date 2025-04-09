import controller.CustomerController;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import repository.Imp.*;
import service.CustomerService;

public class Main {
    public static void main(String[] args) throws SQLException {



        CustomerController customerController = new CustomerController(new CustomerService(new SqlCustomerRep()));
customerController.runMenu();



    }
}
