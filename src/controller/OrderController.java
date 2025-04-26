package controller;

import model.*;
import repository.DatabaseConnection;
import repository.Imp.SqlCustomerRep;
import repository.Imp.SqlOrderRepo;
import repository.Imp.SqlProductRepo;
import repository.ProductRepo;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.SessionManagment;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    OrderService orderService = new OrderService();
    SqlOrderRepo sqlOrderRepo = new SqlOrderRepo();
    private ProductService productService;
    private  CartService cartService;
    public OrderController(CartService cartService)
    {
        this.cartService = cartService;
    }
    public OrderController() {

    }



//implement addCartToOrder()



public List<CartItem> addToCart() throws SQLException {
    Scanner sc = new Scanner(System.in);
    List<CartItem> items;

//filter

    System.out.println("enter F to  filter or press enter to skip");
    String f = sc.nextLine().trim();
    if (f.equalsIgnoreCase("F")) {
        new ProductController().filterByCategoryAndPrice();
    } else {
        new SqlProductRepo().getAllProducts();
    }
    System.out.println("\nEnter Product ID");
    int product_id = sc.nextInt();

    Product product = new SqlProductRepo().getProductById(product_id);
    if (product == null) {
        System.out.println("Product not found");
        return null;
    }
    int quantity = 0;
    do {
        System.out.println("Enter Quantity");

        quantity = sc.nextInt();

        int currentQuantity = product.getQuantity();
        if(quantity==0)
        {
            System.out.println(Colors.RED+"can't add product with 0 quantity"+Colors.RESET);
            return null;

        }
        if (quantity > currentQuantity) {
            System.out.println(Colors.RED + "\nSorry product quantity exceeded there are  "+ currentQuantity + " products in stock"+Colors.RESET);
        }
    } while (quantity > product.getQuantity());
    items = this.cartService.addToCart(product, quantity);

    return items;
}

public void placeOrder(Customer customer)throws SQLException {

       if (this.cartService.isEmpty()) {
           System.out.println("Cart is empty");
       return;
       }
       Order order = new Order(customer);
       List<CartItem> cartItems = this.cartService.getCart();

       for (CartItem item : cartItems) {
          boolean productExists = false;
          for(OrderProducts orderProducts : order.getOrderProducts()) {
              if(orderProducts.getProduct().getProductId() == item.getProduct().getProductId()) {
                orderProducts.setQuantity(orderProducts.getQuantity() + item.getQuantity());
                  productExists = true;
                  break;
              }
          }
          if(!productExists) {
              OrderProducts newOrderProduct = new OrderProducts(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
              order.getOrderProducts().add(newOrderProduct);
          }

       }
       boolean orderSaved = sqlOrderRepo.addOrder(order);
       //update stock
       if (orderSaved) {
           for (OrderProducts op : order.getOrderProducts()) {
               int currentStock = new SqlProductRepo().getProductById(op.getProduct().getProductId()).getQuantity();
               if(op.getQuantity()>currentStock) {
                   System.out.println("empty cart");
                   return;
               }
               currentStock-=op.getQuantity();
               new SqlProductRepo().updateStock(op.getProduct().getProductId(),currentStock);
           }
           System.out.println(Colors.GREEN +"Order successfully added with id : " + order.getId()+Colors.RESET);

           //review
           System.out.println("Review : \n");
           for (CartItem cartItem : this.cartService.getCart()) {
                {
                    System.out.println(cartItem.toString());
                    new ReviewController().addReview(customer.getId(),cartItem);
                }
           }
           cartService.clearCart();
       } else {
           System.out.println(Color.RED+"failed to add Order"+Colors.RESET);
       }
}

    public void orderHistory() throws SQLException {
Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
    List<Order> orders =  orderService.getOrderHistoryForCustomer(id);

for (Order order : orders) {
   if(order==null) {
       System.out.println("customer does not have any order");
   }
    System.out.println(order.toString());
}
}



}



