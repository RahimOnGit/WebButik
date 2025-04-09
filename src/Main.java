import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import repository.Imp.*;
public class Main {
    public static void main(String[] args) throws SQLException {

        SqlCustomerRep customerRep = new SqlCustomerRep();

        customerRep.showCustomers();


    }
}
