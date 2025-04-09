package model;

public class Product {
    int productId;
    String name;
    String description;
    double price;
    int quantity;

    Product(int productId, String name, String description, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
