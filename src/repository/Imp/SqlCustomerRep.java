package repository.Imp;

import model.Customer;
import org.w3c.dom.ls.LSOutput;
import repository.CustomerRepo;

import java.sql.*;

public class SqlCustomerRep  implements CustomerRepo {

    String db = "jdbc:sqlite:webbutiken.db";
    @Override
    public void addCustomer(Customer customer) {


    }

    @Override
    public void UpdateCustomer(int customerId, Customer customer) {

    }

    public void showCustomers() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while(rs.next())
            {
                System.out.println(rs.getString("name"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
