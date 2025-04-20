package repository.Imp;

import com.sun.source.tree.ReturnTree;
import model.Review;
import repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlReviewRepo {
DatabaseConnection db = new DatabaseConnection();


    public boolean insertReview(Review review) throws SQLException {
Connection conn = db.getConnection();
String sql = "insert into reviews (product_id,customer_id,rating,comment) values (?,?,?,?) ";

        PreparedStatement ps = conn.prepareStatement(sql);
     ps.setInt(1,review.getProductId());
     ps.setInt(2,review.getCustomerId());
     ps.setInt(3,review.getRating());
     ps.setString(4,review.getComment());
     ps.executeUpdate();
     return true;
    }
}

