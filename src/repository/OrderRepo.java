package repository;

import model.Order;
import model.OrderProducts;
import model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderRepo {
 List<Order> orderHistory(int customerId);

 boolean addOrder(Order order);

 boolean insertProductToOrder(OrderProducts orderProducts ,Connection conn);

 Order getOrderById(int orderId);
}
