package model;

public class Product {
   private int productId;
   private int manufacturer_id;
    private String  name;
   private String description;
   private double price;
   private int quantity;

    public Product(int productId, int manufacturer_id, String name,String description, double price, int quantity) {
        this.productId = productId;
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(int manufacturer_id, String name,String description, double price, int quantity) {
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }
    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

@Override
    public String toString() {
        return "Product [productId=" + productId + ", manufacturer_id=" + manufacturer_id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity=" + quantity + "]";

}



}
