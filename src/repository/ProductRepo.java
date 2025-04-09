package repository;

import model.Product;

import java.util.List;
public interface ProductRepo{
 Product findByID(int id);
List<Product> getAllProducts();
List<Product> findByName(String name);
List<Product> findByCategory(String category);
void updatePrice(int productId ,double price);
void updateStock(int productId, int stock);
void addProduct(Product product);
}
