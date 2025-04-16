package service;

import model.CartItem;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cart = new ArrayList<>();

    public List<CartItem> addToCart(Product product , int quantity) {

        for(CartItem item : cart) {
            if(item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);

            }
        }
        cart.add(new CartItem(product, quantity));
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


}
