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
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }
    public OrderController() {

    }



//implement addCartToOrder()



public List<CartItem> addToCart() throws SQLException {
    Scanner sc = new Scanner(System.in);
    List <CartItem> items;

//filter

    System.out.println("enter F to  filter or press enter to skip");
    String f = sc.nextLine().trim();
    if(f.equalsIgnoreCase("F")) {
        new ProductController().filterByCategoryAndPrice();
    }
    new SqlProductRepo().getAllProducts();
    System.out.println("\nEnter Product ID");
    int  product_id = sc.nextInt();

         Product product = new SqlProductRepo().getProductById(product_id);
    if(product == null) {
        System.out.println("Product not found");
        return null;
    }
    System.out.println("Enter Quantity");
    int quantity = sc.nextInt();

     items = this.cartService.addToCart(product, quantity);


  //  System.out.println("Product "+this.cartService.getCart().toString()+" added to the cart ");
    for (CartItem cartItem : this.cartService.getCart()) {
     if(cartItem.getProduct().getQuantity()<quantity) {
         System.out.println(Colors.RED+"\nSorry product Quantity Exceeded\n there are "+cartItem.getProduct().getQuantity()+" "+cartItem.getProduct().getName()+"in stock"+Colors.RESET);
     return null;

     }
    }
    return items;
}

public void placeOrder(Customer customer)throws SQLException {

       if (this.cartService.isEmpty()) {
           System.out.println("Cart is empty");
       return;
       }
       Order order = new Order(customer);
       for (CartItem item : this.cartService.getCart()) {
          boolean productExists = false;
          for(OrderProducts orderProducts : order.getOrderProducts()) {
              if(orderProducts.getProduct().getProductId() == item.getProduct().getProductId()) {
                orderProducts.setQuantity(orderProducts.getQuantity() + item.getQuantity());
                  productExists = true;
                  break;
              }
          }
          if(!productExists) {
              OrderProducts orderProduct = new OrderProducts(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
              order.getOrderProducts().add(orderProduct);
              System.out.println(Colors.GREEN +orderProduct.getProduct().getName()+" has been placed successfully"+Colors.RESET);
          }


       }
       boolean orderSaved = sqlOrderRepo.addOrder(order);
       if (orderSaved) {
           for (OrderProducts op : order.getOrderProducts()) {

               int currentStock = new SqlProductRepo().getProductById(op.getProduct().getProductId()).getQuantity();
               System.out.println("stock before ordering  : " + currentStock);
               currentStock-=op.getQuantity();
               System.out.println("stock after : " + currentStock);
               if(currentStock <= 0) {
                   System.out.println("out of stock ");
                   break;
               }
               new SqlProductRepo().updateStock(op.getProduct().getProductId(),currentStock);
           }
           System.out.println(Colors.GREEN +"Order successfully added with id : " + order.getId()+Colors.RESET);

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



