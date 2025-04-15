package repository.Imp;

import model.Product;
import repository.ProductRepo;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlProductRepo implements ProductRepo {
    private final String db = "jdbc:sqlite:webbutiken.db";


    @Override
    public boolean addProduct(Product product) {
        try(Connection conn = DriverManager.getConnection(db)) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (manufacturer_id,name,description,price,stock_quantity) VALUES (?,?,?,?,?)");

            pstmt.setInt(1, product.getManufacturer_id());
            pstmt.setString(2, product.getName());
           pstmt.setString(3, product.getDescription());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantity());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


    @Override
    public boolean updateStock(int productId, int stock)
    {
        try(Connection conn = DriverManager.getConnection(db))
        {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET stock_quantity=? WHERE product_id=?");
            pstmt.setDouble(1,stock );
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //update price
    @Override
    public boolean updatePrice(int productId, double price){
       try(Connection conn = DriverManager.getConnection(db))
       {
           PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET price=? WHERE product_id=?");
 pstmt.setDouble(1, price);
 pstmt.setInt(2, productId);
 pstmt.executeUpdate();
 return true;
       }
       catch (SQLException e)
       {
           System.out.println(e.getMessage());
           return false;
       }
    }

    //get all products
    @Override
    public boolean getAllProducts() {

        try(Connection conn = DriverManager.getConnection(db))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");

            while (rs.next()) {
                System.out.println(rs.getString("name")+
                        "   "+rs.getString("price")+"$"+
                        "   "+rs.getString("stock_quantity")+
                        "   "+rs.getString("description"));
            }
            return true;
        } catch (Exception e) { throw new RuntimeException(e);

        }
        }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement pstmt = conn.prepareStatement("select * from products where name like ?"))
        {
            pstmt.setString(1,"%"+name+"%");
            System.out.println("Searching for " + name);
            System.out.println("sql query :    "+pstmt.toString());
            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("manufacturer_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity")
                    );
                    products.add(p);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public List<Product> findByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT p.product_id, p.manufacturer_id, p.name, p.description, p.price, p.stock_quantity " +
                "FROM products p " +
                "JOIN products_categories pc ON p.product_id = pc.product_id " +
                "WHERE pc.category_id = ?";

        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("manufacturer_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity")
                    );
                    products.add(p);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return products;
    }




    public void showCategory() throws SQLException {

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement pstmt = conn.prepareStatement("select * from categories");
            ResultSet rs = pstmt.executeQuery())
        {
            while (rs.next()) {

                System.out.println(rs.getString("category_id")+ "." + rs.getString("name"));
            }
        }

    }


    public Product getProductById(int productId) throws SQLException {
        Product p = null;
        try (Connection conn = DriverManager.getConnection(db)) {
            PreparedStatement pstmt = conn.prepareStatement("select * from products where product_id=?");
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();


            p = null;
            if (rs.next()) {
                p = new Product(
                        rs.getInt("product_id"),
                        rs.getInt("manufacturer_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"));

            }

        } catch (Exception e) {
            System.out.println("product not found");

        }
        return p;
    }



    public void manufactures() throws SQLException {

        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement pstmt = conn.prepareStatement("select * from manufacturers");
            ResultSet rs = pstmt.executeQuery())
        {
            while (rs.next()) {

                System.out.println(rs.getString("manufacturer_id")+ "." + rs.getString("name"));
            }
        }

    }
    public void delete(int categoryId) {

    }
}
