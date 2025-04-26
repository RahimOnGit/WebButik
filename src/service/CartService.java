package service;

import model.CartItem;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private final List<CartItem> cart = new ArrayList<>();

    public List<CartItem> addToCart(Product product , int quantity) {

        for(CartItem item : cart) {
            if(item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);
return cart;
            }
        }
        CartItem newItem = new CartItem(product, quantity);
        cart.add(newItem);
return cart;
    }

public List<CartItem> getCart() {
        return cart;
}

public double getTotalPrice() {
        return cart.stream().mapToDouble(CartItem::calculateTotalPrice).sum();
}

public void clearCart() {
    cart.clear();

}

public boolean isEmpty()
    {
        return cart.isEmpty();
    }

    public boolean removeFromCart(CartItem cartItem) {
        return cart.remove(cartItem);
    }

    public void changeQuantity(List<CartItem> cartItems,int productId, int newQuantity) {

        for(CartItem item : cartItems) {
            if(item.getProduct().getProductId() == productId) {
                item.setQuantity(newQuantity);
                System.out.println(item.getProduct().getName()+" quantity changed to "+newQuantity);
            }
            else
            {
                System.out.println("not found");
            }
        }
    }
}
