package service;

import model.Customer;
import model.Order;
import model.OrderProducts;
import model.Product;
import repository.Imp.SqlOrderRepo;
import repository.Imp.SqlProductRepo;

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
    public List<Order> getOrderHistoryForCustomer(int customerId) throws SQLException {
        return sqlOrderRepo.orderHistory(customerId);
    }

    public boolean addOrder(Order order) throws SQLException {
        return sqlOrderRepo.addOrder(order);
    }

//


}