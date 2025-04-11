package service;

import model.Product;
import repository.Imp.SqlProductRepo;
import repository.ProductRepo;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService  {
private final ProductRepo productRepo;
public ProductService(ProductRepo productRepo) {
    this.productRepo = productRepo;
}

public void showProducts() throws SQLException {
    boolean res = productRepo.getAllProducts();
    System.out.println(res?"Displaying products...":"No products found");

}

public void findProduct(String name) throws SQLException {
    List<Product> products = productRepo.findByName(name);

        System.out.println("Found "+products.size()+" products");
        for(Product product : products){
            System.out.println(product.toString());
        }
    }
 public boolean findByCategory(int id) throws SQLException {

     List<Product> products = productRepo.findByCategory(id);
     if(products.size()>0){
         for(Product product : products){
             System.out.println(product.toString());
         }

         return true;
     }
     else { return false; }

 }




}