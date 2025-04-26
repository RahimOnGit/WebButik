package service;

import model.*;
import repository.DatabaseConnection;
import repository.Imp.SqlOrderRepo;
import repository.Imp.SqlProductRepo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private SqlOrderRepo sqlOrderRepo;
    private SqlProductRepo sqlProductRepo;

    public OrderService() {
this.sqlOrderRepo = new SqlOrderRepo();
this.sqlProductRepo = new SqlProductRepo();
    }
//methods

    //order history for customer
    public List<Order> getOrderHistoryForCustomer(int customerId) {
        return sqlOrderRepo.orderHistory(customerId);
    }

    public boolean addOrder(Order order) throws SQLException {
        return sqlOrderRepo.addOrder(order);
    }

    public CartItem addProductsToCart(OrderProducts op)throws SQLException {
        try(Connection conn = DriverManager.getConnection(DatabaseConnection.url))
        {
           return sqlOrderRepo.insertProductsToCart(op,conn);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
//


}