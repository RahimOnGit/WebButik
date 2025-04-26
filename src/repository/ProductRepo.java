package repository;

import model.Product;

import java.util.List;
public interface ProductRepo{
List<Product> getAll();
boolean getAllProducts();
List<Product> findByName(String name);
List<Product> findByCategory(int categoryId);
boolean updatePrice(int productId , double price);
boolean updateStock(int productId, int stock);
boolean addProduct(Product product);
}

