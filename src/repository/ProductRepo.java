package repository;

import model.Product;

import java.util.List;
public interface ProductRepo{
boolean getAllProducts();
List<Product> findByName(String name);
List<Product> findByCategory(int categoryId);
boolean updatePrice(int productId , double price);
boolean updateStock(int productId, int stock);
boolean addProduct(Product product);
}
//3.2 Produkter
//(DONE)__________Visa alla produkter: Möjlighet att visa alla produkter från products-tabellen (SELECT * FROM products).
//(DONE)__________Sök produkt efter namn: Möjlighet att söka efter produkter baserat på namn (SELECT * FROM products WHERE name LIKE).
//(DONE)__________Sök produkt efter kategori: Möjlighet att söka efter produkter baserat på kategori (SELECT med JOIN på products och product_categories).
//(DONE)Uppdatera pris: Möjlighet att ändra priset för en produkt.
//(DONE)Uppdatera lagersaldo: Möjlighet att ändra lagersaldot (stock_quantity) för en produkt.

//Lägg till ny produkt: Möjlighet att lägga till en ny produkt i products-tabellen (INSERT).

//UPNEXT

