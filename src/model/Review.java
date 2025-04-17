package model;

public class Review {

    private int id;
    private Product product;
    private Customer customer;
    private int rating;
    private String comment;

    public Review(int id, Product product, Customer customer, int rating, String comment) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
    }
    public Review(Product product, Customer customer, int rating, String comment) {
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review { product: " + product.getName() +", rating= " + rating+"/5 "+", comment=" + comment +  ", customer: " + customer.getName() +'}';
    }
}
