package controller;

import model.CartItem;
import model.Customer;
import service.CartService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CartController {
 private final CartService cartService;
   private final OrderController orderController;
   public CartController(){
       this.cartService = new CartService();
       this.orderController = new OrderController(cartService);
   }

    public void cartMenu(Customer customer) throws SQLException {
        Scanner sc = new Scanner(System.in);
        CartItem cartItem = new CartItem();
        List<CartItem> cartItems = List.of();
        while (true) {
            System.out.println("\nCart Menu:");
            System.out.println("1. Add product to cart");
            System.out.println("2. View cart");
            System.out.println("3. Place order");
            System.out.println("4. Back");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 ->  cartItems = orderController.addToCart();
                case 2 -> {
                    System.out.println("your items :");
                    
                        for(CartItem items : cartItems)
                        {
                            System.out.println(items.toString());
                        }    
                    
                    
                    }
                case 3 -> orderController.placeOrder(customer);
                case 4 -> { return; }
                default -> System.out.println(" Invalid option");
            }
        }
    }

    public void getCartItems() throws SQLException {
        System.out.println(cartService.getCart().toString());
    }



}
