package repository.Imp;

import model.Customer;
import repository.CustomerRepo;

import java.sql.*;

public class SqlCustomerRep  implements CustomerRepo {
Customer customer;

    String db = "jdbc:sqlite:webbutiken.db";
    @Override
    public boolean addCustomer(Customer customer)  {
try {
    Connection conn = DriverManager.getConnection(db);
    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (name,email,phone,address,password) VALUES (?,?,?,?,?)");
    pstmt.setString(1, customer.getName());
    pstmt.setString(2,customer.getEmail());
    pstmt.setString(3,customer.getPhone());
    pstmt.setString(4,customer.getAddress());
    pstmt.setString(5,customer.getPassword());
    pstmt.executeUpdate();
    pstmt.close();
    conn.close();
    System.out.println("Customer added successfully");

}
catch (SQLException e) {
    System.out.println(" this email is already added");
}


        return false;
    }

    @Override
    public void UpdateCustomer(int customerId, Customer customer) {
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
    pstmt.close();
    conn.close();
    System.out.println("Customer updated successfully");


} catch (Exception e) {
    System.out.println(e.getMessage()+ "error updating customer "+customerId);
}



    }

    public void showCustomers() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(db);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while(rs.next())
            {
                System.out.println(rs.getInt("customer_id")+"   "+rs.getString("name"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
