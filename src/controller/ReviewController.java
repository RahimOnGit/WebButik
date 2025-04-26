package controller;

import model.CartItem;
import model.Customer;
import model.Product;
import model.Review;
import repository.Imp.SqlOrderRepo;
import service.OrderService;
import service.ReviewService;

import java.sql.SQLException;
import java.util.Scanner;

public class ReviewController {
    ReviewService reviewService = new ReviewService();

    public void addReview(int customerId, CartItem item) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int productId;
        //while (true) {
            System.out.println("Review for : "+item.getProduct().getName()+"\nenter 0 to skip");
//            productId = sc.nextInt();
//            if (productId == 0) {
//                System.out.println("review skipped");
//                return;
//            }
//
//
//            if (item.getProduct().getProductId() == productId) {
//                break;
//            } else {
//                System.out.println("sorry can't review this product\npurchased products only");
//            }
//        }
        int rating;
while(true) {
    System.out.println("rate between 1-5 :");
    rating = sc.nextInt();

    if(rating ==0) {
        System.out.println("review skipped");
        return;
    }
    if(rating >= 1 && rating <= 5) {
        break;
    }
    System.out.println(Colors.RED+"rating must be between 1 and 5"+Colors.RESET);
    }


        sc.nextLine();
            System.out.println("comment :");
            String comment = sc.next();
            reviewService.insertReview(item.getProduct().getProductId(), customerId, rating, comment);
        System.out.println(Colors.GREEN+"review submitted successfully"+Colors.RESET);
        }
    }

