package repository.Imp;

import model.Customer;
import repository.CustomerRepo;

import java.sql.*;

public class SqlCustomerRep  implements CustomerRepo {

    String db = "jdbc:sqlite:webbutiken.db";
    @Override
    public boolean addCustomer(Customer customer)  {

try(
    Connection conn = DriverManager.getConnection(db);
    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (name,email,phone,address,password) VALUES (?,?,?,?,?)")){
    pstmt.setString(1, customer.getName());
    pstmt.setString(2,customer.getEmail().trim());
    pstmt.setString(3,customer.getPhone());
    pstmt.setString(4,customer.getAddress());
    pstmt.setString(5,customer.getPassword());
    pstmt.executeUpdate();
    return true;

}
catch (SQLException e) {
    System.out.println("Sql error in addCustomer "+e.getMessage()+" the email "+customer.getEmail()+" is already added");

return false;
}
    }

    @Override
    public boolean UpdateCustomer(int customerId, Customer customer) {
try {

    Connection conn = DriverManager.getConnection(db);

    PreparedStatement pstmt = conn.prepareStatement("UPDATE CUSTOMERS SET NAME=?,EMAIL=?,PHONE=?,ADDRESS=?,PASSWORD=? WHERE customer_id=?");
    pstmt.setString(1, customer.getName());
    pstmt.setString(2,customer.getEmail());
    pstmt.setString(3,customer.getPhone());
    pstmt.setString(4,customer.getAddress());
    pstmt.setString(5,customer.getPassword());
    pstmt.setInt(6, customerId);


    pstmt.executeUpdate();

    return true;


} catch (Exception e) {
    System.out.println(e.getMessage());
return false;
}

    }

    public void showCustomers() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while(rs.next())
            {
                System.out.println(rs.getInt("customer_id")+"   "+rs.getString("name")+"    "+rs.getString("email")+"  "+rs.getString("phone")+"   "+rs.getString("address")+"     "+rs.getString("password"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteCustomer(int customerId) throws SQLException {
        try( Connection conn = DriverManager.getConnection(db);
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customers WHERE customer_id=?");
        )
        {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            System.out.println("Customer deleted successfully");

        } catch (Exception e) { throw new RuntimeException(e);}

    }

}
