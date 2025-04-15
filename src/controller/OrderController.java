package controller;

import model.Customer;
import model.Order;
import model.OrderProducts;
import model.Product;
import repository.Imp.SqlCustomerRep;
import repository.Imp.SqlOrderRepo;
import repository.Imp.SqlProductRepo;
import service.OrderService;
import service.ProductService;
import service.SessionManagment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    OrderService orderService = new OrderService();
    SqlOrderRepo sqlOrderRepo = new SqlOrderRepo();





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

void addOrder() throws SQLException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter customer id");
    int customerId = sc.nextInt();

Customer cusomter= new SqlCustomerRep().findById(customerId);
if(cusomter == null) {
    System.out.println("Customer not found");
    return;
}
Order order = new Order(cusomter);
    System.out.println("Enter product id");
    new ProductService(new SqlProductRepo()).showProducts();
    int productId = sc.nextInt();
Product product = new SqlProductRepo().getProductById(productId);
if(product == null) {
    System.out.println("Product not found");
}

    System.out.println("Enter quantity");
int quantity = sc.nextInt();
OrderProducts op = new OrderProducts(order,product,quantity,product.getPrice());
order.getOrderProducts().add(op);


    boolean res = sqlOrderRepo.addOrder(order);
    System.out.println(res?"successfully added with id :"+order.getId():"X failed to add order ");
}

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



