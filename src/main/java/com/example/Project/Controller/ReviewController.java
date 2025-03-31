package com.example.Project.Controller;

import com.example.Project.Entity.Review;
import com.example.Project.Service.ReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Get all reviews
    @GetMapping("/get")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Get review by ID
    @GetMapping("/get/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // Add new review
    @PostMapping("/post")
    public String addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return "Review successfully added";
    }

    // Update review details
    @PutMapping("/update/{id}")
    public String updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        return reviewService.updateReview(id, updatedReview);
    }

    // Delete review by ID
    @DeleteMapping("/delete/{id}")
    public String deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        return "Deleted successfully";
    }

    // Delete all reviews
    @DeleteMapping("/deleteall")
    public String deleteAllReviews() {
        reviewService.deleteAllReviews();
        return "Deleted all reviews";
    }

    // Get reviews by shop name
    @GetMapping("/shop")
    public List<Review> getReviewsByShopName(@RequestParam String shopName) {
        return reviewService.findByShopName(shopName);
    }

    // Paginate and sort reviews
    @GetMapping("/pagination")
    public List<Review> paginate(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, 
                                 @RequestParam(defaultValue = "asc") String direction) {
        return reviewService.paginate(page, size, sortBy, direction);
    }

    // Get reviews by rating range
    @GetMapping("/ratingRange")
    public List<Review> getReviewsByRatingRange(@RequestParam Double minRating, @RequestParam Double maxRating) {
        return reviewService.findByRatingRange(minRating, maxRating);
    }

    // Get reviews by service type
    @GetMapping("/serviceType")
    public List<Review> getReviewsByServiceType(@RequestParam String serviceType) {
        return reviewService.findByServiceType(serviceType);
    }
}
