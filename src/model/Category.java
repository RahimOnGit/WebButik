package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class Category{
    int id;
    String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean assignProductToCategory(Product p, Category c);
}
 class ProductCategory extends Category {
    String db = "jdbc:sqlite:webbutiken.db";
    int productCategoryId;
    Category category;
    Product product;

    ProductCategory(int productCategoryId, Category category, Product product) {
        super(productCategoryId, category.name);
        this.productCategoryId = productCategoryId;
        this.category = category;
        this.product = product;
    }

    @Override
    public boolean assignProductToCategory(Product p, Category c) {


        try (Connection conn = DriverManager.getConnection(db);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product_category(product_category_id,product_id) VALUES(?,?)")

        ) {
            pstmt.setInt(1, p.getProductId());
            pstmt.setInt(2, c.getId());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}


