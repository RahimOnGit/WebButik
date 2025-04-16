package repository.Imp;

import model.*;
import repository.OrderRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlOrderRepo implements OrderRepo {
    private final String db = "jdbc:sqlite:webbutiken.db";

    public CartItem addProductToCart(Product product , int quantity) {
        return new CartItem(product, quantity);
    }




    public Order addOrder(Customer customer,OrderProducts op)
    {
        String orderSql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(db)) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, customer.getId());

                pstmt.setDate(2, Date.valueOf(op.getOrder().getOrderDate()));
                pstmt.executeUpdate();

                // Get generated order ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        op.getOrder().setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }

                for (OrderProducts p : op.getOrder().getOrderProducts()) {
                    p.setOrder(op.getOrder());
                    insertProductToOrder(op, conn);
                }

                conn.commit();
                return op.getOrder();

            } catch (Exception e) {
                conn.rollback();
                System.out.println("addOrder() transaction failed: " + e.getMessage());
                return null;
            }

        } catch (Exception e) {
            System.out.println("addOrder() connection failed: " + e.getMessage());
            return null;
        }

    }

    @Override
    public boolean addOrder(Order order) {
        String orderSql = "INSERT INTO orders (customer_id, order_date) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(db)) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, order.getCustomer().getId());
                pstmt.setDate(2, Date.valueOf(order.getOrderDate()));
                pstmt.executeUpdate();

                // Get generated order ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }

                for (OrderProducts op : order.getOrderProducts()) {
                    op.setOrder(order);
                    insertProductToOrder(op, conn);
                }

                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback();
                System.out.println("addOrder() transaction failed: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("addOrder() connection failed: " + e.getMessage());
            return false;
        }
    }
    public boolean insertProductToOrder(OrderProducts orderProducts, Connection conn) {
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";


        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, orderProducts.getOrder().getId());
            pstmt.setInt(2, orderProducts.getProduct().getProductId());
            pstmt.setInt(3, orderProducts.getQuantity());
            pstmt.setDouble(4, orderProducts.getUnitPrice());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    orderProducts.setId(generatedId);
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("insertProductToOrder() error: " + e.getMessage());
            return false;
        }
    }


    public CartItem insertProductsToCart(OrderProducts orderProducts, Connection conn) {

        String sql = "INSERT INTO orders_products (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderProducts.getId());
            pstmt.setInt(2, orderProducts.getProduct().getProductId());
            pstmt.setInt(3, orderProducts.getQuantity());
            pstmt.setDouble(4, orderProducts.getProduct().getPrice());
            pstmt.executeUpdate();

            return new CartItem(orderProducts.getProduct(),orderProducts.getQuantity());

        } catch (Exception e) {
            System.out.println("insertCartItemToOrder() error: " + e.getMessage());
        return null;
        }
    }


    @Override
    public Order getOrderById(int orderId) {
        Order order = null;

        try (Connection conn = DriverManager.getConnection(db)) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                Customer customer = new SqlCustomerRep().findById(customerId);

                order = new Order(
                        rs.getInt("order_id"),
                        customer,
                        rs.getDate("order_date").toLocalDate()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return order;
    }

    public List<Order> orderHistory(int customerId) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db)) {
            String sql = """
                SELECT 
                    o.order_id,
                    o.customer_id,
                    o.order_date,
                    p.product_id,
                    p.name AS product_name,
                    p.description,
                    p.price,
                    p.stock_quantity AS product_quantity,
                    p.manufacturer_id,
                    op.quantity AS order_quantity,
                    op.unit_price
                FROM orders o
                JOIN orders_products op ON op.order_id = o.order_id
                JOIN products p ON p.product_id = op.product_id
                WHERE o.customer_id = ?
            """;

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, customerId);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("order_id");

                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("manufacturer_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("product_quantity")
                    );

                    OrderProducts orderProduct = new OrderProducts(
                            null,
                            product,
                            rs.getInt("order_quantity"),
                            rs.getDouble("unit_price")
                    );

                    Order existingOrder = orders.stream()
                            .filter(o -> o.getId() == orderId)
                            .findFirst()
                            .orElse(null);

                    if (existingOrder != null) {
                        orderProduct.setOrder(existingOrder);
                        existingOrder.getOrderProducts().add(orderProduct);
                    } else {
                        Customer customer = new SqlCustomerRep().findById(rs.getInt("customer_id"));
                        Order newOrder = new Order(
                                orderId,
                                customer,
                                rs.getDate("order_date").toLocalDate()
                        );
                        orderProduct.setOrder(newOrder);
                        newOrder.getOrderProducts().add(orderProduct);

                        orders.add(newOrder);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order history: " + e.getMessage(), e);
        }

        return orders;
    }
}
