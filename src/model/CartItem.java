package model;

public class CartItem {
    private Product product;
    private int quantity;
    private double unitPrice;
    public CartItem(Product product, int quantity) {
        this.product = product;
       setQuantity(quantity);

    }
//    public CartItem(Product product, int quantity,double unitPrice) {
//        this.product = product;
//        setQuantity(quantity);
//        setUnitPrice(unitPrice);
//
//
//    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public CartItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {

            System.out.println("quantity should be greater than 0");
        return;
        }
        this.quantity = quantity;

    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double calculateTotalPrice() {
        return product.getPrice() * quantity;
    }

@Override
public String toString() {
        return "CartItem {" + "product : " + product.getName() +" price : "+product.getPrice() + ", quantity=" + quantity+", totalPrice: " + calculateTotalPrice() + '}';

}
}
