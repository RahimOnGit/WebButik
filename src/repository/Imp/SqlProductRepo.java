package repository.Imp;

import model.Product;
import repository.ProductRepo;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlProductRepo implements ProductRepo {
    String db = "jdbc:sqlite:webbutiken.db";

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
        try(Connection conn = DriverManager.getConnection(db);
            PreparedStatement pstmt = conn.prepareStatement("select * from products where manufacturer_id = ?"))
        {
            pstmt.setInt(1,categoryId);
            System.out.println("Searching for " + categoryId);

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
}
