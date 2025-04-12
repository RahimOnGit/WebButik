package controller;

import repository.Imp.SqlProductRepo;
import repository.ProductRepo;
import service.ProductService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    ProductService product = new ProductService(new SqlProductRepo());

    public void runMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("1. Show all products\n2. Search products\n3. Delete product\n4. Update product");
        choice = scanner.nextInt();

        switch(choice) {
            case 1:
                showProducts();
                break;
            case 2:
                search();
                break;
            case 3:
                System.out.println("Delete not available yet");
                break;
            case 4:
                updateProduct();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void showProducts() throws SQLException {
        product.showProducts();
    }

    public void search() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Search by name\n2. Search by category\n3. Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    boolean res = searchByName();
                    if (!res) {
                        System.out.println("Search failed. Please enter a valid product name.");
                    }
                    break;
                case 2:
                    searchByCategory();
                    break;
                    case 3:
                    System.out.println("Exiting search...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 3);
    }


    public void updateProduct()
    {
        System.out.println("1.Update product price\n2.Update product stock quantity\n3.exit");
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {


            choice= scanner.nextInt();
            switch (choice) {
                case 1:
                    updatePrice();
                case 2:
                    updateStock();
                case 3:
                    break;

            }
        } while(choice==3);


    }


    public void updatePrice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productId = scanner.nextInt();
        System.out.println("Enter the new price: ");
        double price = scanner.nextDouble();
        product.updatePrice(productId, price);

    }

    public void updateStock(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int productId = scanner.nextInt();
        System.out.println("Enter the stock quantity: ");
        int stock = scanner.nextInt();
        product.updateStock( productId,stock);

    }

    public boolean searchByName() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product name: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            return false;
        } else {
            product.findProduct(name);
            return true;
        }
    }
public void searchByCategory() throws SQLException {
       new SqlProductRepo().showCategory();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product category: ");
        int c = scanner.nextInt();
       product.findByCategory(c);


}


        }


