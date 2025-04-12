package repository.Imp;

import model.Order;
import repository.OrderRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlOrderRepo implements OrderRepo {
    private final String db = "jdbc:sqlite:webbutiken.db";


    @Override
    public List<Order> orderHistory(int customerId) {
       List<Order> orders = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(db))
       {
           String sql = "select customers.id as customer_id, CUSTOMERS.name as customer_name, orders.order_id as order_id, orders.order_date  , PRODUCTS.name as product_name,ORDERS_PRODUCTS.quantity ,Orders_products.unit_price \n" +
                   "FROM ORDERS\n" +
                   "JOIN CUSTOMERS on CUSTOMERS.customer_id = ORDERS.customer_id\n" +
                   "join orders_products on orders_products.order_id = orders.order_id \n" +
                   "Join PRODUCTS on PRODUCTS.product_id = ORDERS_PRODUCTS.product_id;\n" +
                   "WHERE CUSTOMERS.customer_id = ? ;";
           try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
               ResultSet rs = pstmt.executeQuery();
               pstmt.setInt(1, customerId);

               while(rs.next()) {
                   orders.add(new Order(rs.getInt("order_id"),rs.getInt("customer_id"),rs.getDate("orders.order_date")));

               }
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return orders;
    }

    @Override
    public boolean addOrder(Order order) {
        try(Connection conn = DriverManager.getConnection(db)) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orders  (customer_id) VALUES (?)");

            pstmt.setInt(1, order.getCustomerId());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
