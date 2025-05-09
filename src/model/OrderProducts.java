package model;

public class OrderProducts  {
    int id;
    Order order;
    Product product;
    int quantity;
    double unitPrice;
    CartItem cartItem;




    public OrderProducts(Order order, Product product, int quantity, double unitPrice) {
        this.order = order;
        this.product = product;
        this.quantity =  quantity;
        this.unitPrice = unitPrice;
    }

    public OrderProducts(int id, Order order, Product product, int quantity, double unitPrice) {
        this.id = id;
        this.order = order;
        this.product = product;
        setQuantity(quantity);
        this.unitPrice = unitPrice;
    }

    public OrderProducts(int id, Order order,CartItem cartItem , double unitPrice) {
        this.id = id;
        this.order = order;
        this.cartItem = cartItem;
        this.unitPrice = unitPrice;
    }

    public OrderProducts(Order order, CartItem cartItem, double price) {
        this.order = order;
        this.product = cartItem.getProduct();
        this.quantity = cartItem.getQuantity();
        this.unitPrice = price;

    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        this.quantity = quantity;
    }



    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {

            if (unitPrice < 0) {
                throw new IllegalArgumentException("Unit price cannot be negative");
            }
            this.unitPrice = unitPrice;
        }

    public double calculateTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {

        return "{ product:  " + product.getName() + ", quantity=" + quantity +
                ", unitPrice=" + unitPrice + ", totalPrice=" + calculateTotalPrice() + '}';

    }
}
