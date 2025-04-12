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


    public void updateStock(int productId, int stock){
        //check product no found
        boolean res = productRepo.updateStock(productId, stock);

        System.out.println(res?"stock updated successfully :)":"failed to  updated the price :(");
    }


public void updatePrice(int productId, double price){
    //check product no found
    boolean res = productRepo.updatePrice(productId, price);

    System.out.println(res?"price updated successfully :)":"failed to  updated the price :(");
}


public void showProducts() throws SQLException {
    boolean res = productRepo.getAllProducts();
    System.out.println(res?"Displaying products...":"No products found");

}

public void findProduct(String name)  {
    List<Product> products = productRepo.findByName(name);

        System.out.println("Found "+products.size()+" products");
        for(Product product : products){
            System.out.println(product.toString());
        }
    }
 public void findByCategory(int id)  {

     List<Product> products = productRepo.findByCategory(id);
     System.out.println("Found "+products.size()+" products");
     if(products.size()>0){
         for(Product product : products){
             System.out.println(product.getName()+"\t"+product.getDescription()+"\t"+product.getPrice()+"\t"+product.getQuantity());
         }
     }
    }


 }




