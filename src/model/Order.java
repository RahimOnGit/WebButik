package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//check this class is a sub of order_products ?
//or figure out how the function order history should display the data of other tables


public class Order {
    private int id;
    private Customer customer;
    private LocalDate orderDate;
    private List<OrderProducts> orderProducts;

    public Order(int id, Customer customer, LocalDate orderDate) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderProducts = new ArrayList<>();

    }

    public Order() {
        this.orderProducts = new ArrayList<>();
        this.orderDate = LocalDate.from(LocalDateTime.now());
    }


    public Order(Customer customer) {
        this.customer = customer;
        this.orderProducts = new ArrayList<>();
        this.orderDate = LocalDate.from(LocalDateTime.now());
    }


    public List<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = LocalDate.from(orderDate);
    }

    @Override
    public String toString() {
        return "order id=" + id + ", order date=" + orderDate+ " , Products=" + getOrderProducts().toString();
    }
}

