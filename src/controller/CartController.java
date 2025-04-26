package controller;

import model.CartItem;
import model.Customer;
import repository.Imp.SqlProductRepo;
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


    public void viewCart(List<CartItem> cartItems) throws SQLException {
        Scanner sc = new Scanner(System.in);

        if(cartItems==null || cartItems.isEmpty()){
            System.out.println("empty cart");
            return;
        }

        System.out.println(Colors.BLUE+"order total price : "+cartService.getTotalPrice()+Colors.RESET);
        System.out.println("your items :");
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
                int quantity = 0;
                int currentQuantity=0;
                do {
                    System.out.println("Enter Quantity");

                    quantity = sc.nextInt();
                    currentQuantity = new SqlProductRepo().getProductById(pId).getQuantity();
                    if (quantity > currentQuantity) {
                        System.out.println(Colors.RED + "\nSorry product quantity exceeded there are  "+ currentQuantity + " products in stock"+Colors.RESET);
                    }
                } while (quantity > currentQuantity);
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
    public void changeQuantity(List<CartItem> cartItem,int productId, int newQuantity) throws SQLException {

        cartService.changeQuantity(cartItem,productId,newQuantity);

    }
}




