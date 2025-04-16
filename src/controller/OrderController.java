package controller;

import model.*;
import repository.DatabaseConnection;
import repository.Imp.SqlCustomerRep;
import repository.Imp.SqlOrderRepo;
import repository.Imp.SqlProductRepo;
import service.CartService;
import service.OrderService;
import service.ProductService;
import service.SessionManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    OrderService orderService = new OrderService();
    SqlOrderRepo sqlOrderRepo = new SqlOrderRepo();
    private ProductService productService;
    private final CartService cartService = new CartService();


//implement addCartToOrder()


public void runMenu() throws SQLException {
    Scanner sc = new Scanner(System.in);
    int choice;
    System.out.println("Welcome to the Order Menu");
signIn();
    System.out.println("1. Add Order");
    System.out.println("2. Add Product to Order");
    System.out.println("3. show customer history orders");
    System.out.println("4. exit");
    choice = sc.nextInt();
    switch (choice) {
        case 1:
            addOrder();
        case 2:
            System.out.println("not available yet");
            break;
            case 3:
                System.out.println("not available yet");
                break;
                case 4:
                    System.exit(0);
                    break;

    }
}

public List<CartItem> addToCart() throws SQLException {
    Scanner sc = new Scanner(System.in);
    List <CartItem> items = new ArrayList<>();
    new SqlProductRepo().getAllProducts();

    System.out.println("Enter Product ID");
    int product_id = sc.nextInt();
    Product product = new SqlProductRepo().getProductById(product_id);
    if(product == null) {
        System.out.println("Product not found");
        return null;
    }
    System.out.println("Enter Quantity");
    int quantity = sc.nextInt();

     items = cartService.addToCart(product, quantity);


    System.out.println("Product "+cartService.getCart().toString()+"added to the cart ");
    for (CartItem cartItem : cartService.getCart()) {
        System.out.println(cartItem);

    }
    return items;
}

public void placeOrder(Customer customer){
   try(Connection conn = DriverManager.getConnection(DatabaseConnection.url))
   {

       if (cartService.isEmpty()) {
           System.out.println("Cart is empty");
       return;
       }

       Order order = new Order(customer);
       for (CartItem item : cartService.getCart()) {

           OrderProducts orderProduct = new OrderProducts(order, item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
           order.getOrderProducts().add(orderProduct);

       }

       boolean orderSaved = sqlOrderRepo.addOrder(order);

       if (orderSaved) {
           for (OrderProducts op : order.getOrderProducts()) {
               sqlOrderRepo.insertProductToOrder(op,conn);
           }

           System.out.println("Order successfully added with id : " + order.getId());
           cartService.clearCart();
       } else {
           System.out.println("failed to add Order");
       }
   }
    catch (SQLException e) {
        System.out.println(e.getMessage());

    }

}






public void addCartToOrder(Customer customer) throws SQLException {
   Scanner sc = new Scanner(System.in);
    System.out.println("Enter product id :");
    new ProductService(new SqlProductRepo()).showProducts();

    int productId = sc.nextInt();
    sc.nextLine();
    Product product = new SqlProductRepo().getProductById(productId);
    if(product == null) {
        System.out.println("Product not found");
    }
;
    System.out.println("Enter quantity");
    int quantity = sc.nextInt();
    CartItem cartItem = new CartItem(product, quantity);
    Order order = new Order(customer);
    OrderProducts op = new OrderProducts(order,cartItem,product.getPrice());
    order.getOrderProducts().add(op);

    boolean res = orderService.addOrder(order);
    System.out.println(res?" Order added with id : "+order.getId(): "failed to add order");

        //CartItem cartItem =  orderService.addProductsToCart();


}

void addOrder() throws SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter customer id");
    int customerId = sc.nextInt();

Customer customer= new SqlCustomerRep().findById(customerId);
if(customer == null) {
    System.out.println("Customer not found");
    return;
}
Order order = new Order(customer);
    System.out.println("Enter product id");
    new ProductService(new SqlProductRepo()).showProducts();
    int productId = sc.nextInt();
Product product = new SqlProductRepo().getProductById(productId);
if(product == null) {
    System.out.println("Product not found");
}

    System.out.println("Enter quantity");
int quantity = sc.nextInt();
//unlock them if u want to acitve this cfucntion
//OrderProducts op = new OrderProducts(order,product,quantity,product.getPrice());
//order.getOrderProducts().add(op);




    boolean res = sqlOrderRepo.addOrder(order);
    System.out.println(res?"successfully added with id :"+order.getId():"X failed to add order ");
}
//
//    void addProductToCart(Customer id) throws SQLException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter customer id");
//        int customerId = sc.nextInt();
//        CartItem cartItem = new CartItem();
//        List<CartItem> cartItems;
//        //Order order = new Order(cusomter);
//        System.out.println("Enter product id");
//        new ProductService(new SqlProductRepo()).showProducts();
//        int productId = sc.nextInt();
//        Product product = new SqlProductRepo().getProductById(productId);
//        if(product == null) {
//            System.out.println("Product not found");
//        }
//
//        System.out.println("Enter quantity");
//        int quantity = sc.nextInt();
//        OrderProducts op = new OrderProducts(order,product,quantity,product.getPrice());
//        order.getOrderProducts().add(op);
//
//
//        boolean res = sqlOrderRepo.addOrder(order);
//        System.out.println(res?"successfully added with id :"+order.getId():"X failed to add order ");
//    }



void signIn() throws SQLException {

    Scanner sc = new Scanner(System.in);
    System.out.println("1.Login\n2.Register");
    int choice = Integer.parseInt(sc.nextLine());
    switch (choice) {
        case 1: {
            System.out.println("Enter email");
            String email = sc.nextLine().trim();

            System.out.println("Enter password");
            String password = sc.nextLine().trim();
            new SessionManagment().login(email, password);
        }
case 2:
        new CustomerController().addCustomer();
    }

    }


    public void orderHistory() throws SQLException {
Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = sc.nextInt();
    List<Order> orders =  orderService.getOrderHistoryForCustomer(id);

for (Order order : orders) {
   if(order==null) {
       System.out.println("customer does not have any order");
   }
    System.out.println(order.toString());
}
}



}



