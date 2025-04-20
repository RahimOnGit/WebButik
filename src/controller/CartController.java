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

    public CartController() {
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
                case 1 -> cartItems = orderController.addToCart();
                case 2 -> viewCart(cartItems);
                case 3 -> orderController.placeOrder(customer);
                case 4 -> {
                    return;
                }
                default -> System.out.println(" Invalid option");
            }
        }
    }

    public void getCartItems() {
        System.out.println(cartService.getCart().toString());
    }


    public void viewCart(List<CartItem> cartItems)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("order total price : "+cartService.getTotalPrice());


        System.out.println("your items :");

        boolean res = cartItems.isEmpty();
        if (res)
        {
            System.out.println("Your cart is empty");
            return;
        }

        for (CartItem items : cartItems) {
            System.out.println(items.toString());

        }


        System.out.println("1.remove product from cart\n2.change product quantity\n0.back ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 :
                    {
                        System.out.println("enter product id to remove from cart\n");
                        int productId = sc.nextInt();
                        removeProductFromCart(cartItems, productId);
                        return;
                    }
            case 2:
                System.out.println("enter product id to change quantity\n");
                int pId = sc.nextInt();
                System.out.println("enter quantity\n");
                int quantity = sc.nextInt();
                    changeQuantity(cartItems,pId,quantity);
                    return;

            case 0: {
                return;
            }
            default : System.out.println(" Invalid option");
        }
    }

    public void removeProductFromCart(List<CartItem> cartItem, int productId){
        CartItem itemToRemove = null;
        for (CartItem item : cartItem) {
            if (item.getProduct().getProductId() == productId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            cartItem.remove(itemToRemove);
            System.out.println("Product: "+itemToRemove.getProduct().getName()+" removed from cart successfully");
        }
        else {
            System.out.println("Product: "+productId + " not found");
        }
    }
    //do it later
    public void changeQuantity(List<CartItem> cartItem,int productId, int newQuantity) {

        cartService.changeQuantity(cartItem,productId,newQuantity);

    }
}




