package service;

import model.Customer;
import model.Product;
import model.Review;
import repository.Imp.SqlReviewRepo;

import java.sql.SQLException;

public class ReviewService {
    SqlReviewRepo reviewRepo = new SqlReviewRepo();

    public Review insertReview(int p, int c , int rating , String comment ) throws SQLException {
       Review review = new Review(p,c,rating,comment);
       boolean res = reviewRepo.insertReview(review);
       if(res) {
           System.out.println("Review Inserted");
           return review;

       }
       else
       {
           System.out.println("Review Not Inserted");
           return null;
       }
        }


}
