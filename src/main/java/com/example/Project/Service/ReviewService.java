package com.example.Project.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.Project.Entity.Review;
import com.example.Project.Repository.ReviewRepo;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public void addReview(Review review) {
        reviewRepo.save(review);
    }

    public String updateReview(Long id, Review updatedReview) {
        return reviewRepo.findById(id).map(existingReview -> {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            existingReview.setShopName(updatedReview.getShopName());
            existingReview.setServiceType(updatedReview.getServiceType());
            existingReview.setAppointmentId(updatedReview.getAppointmentId());
            existingReview.setDate(updatedReview.getDate());
            reviewRepo.save(existingReview);
            return "Review updated successfully";
        }).orElse("Review not found with ID: " + id);
    }

    public void deleteReviewById(Long id) {
        reviewRepo.deleteById(id);
    }

    public void deleteAllReviews() {
        reviewRepo.deleteAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id).orElse(null);
    }

    public List<Review> paginate(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") 
                    ? Sort.by(sortBy).descending() 
                    : Sort.by(sortBy).ascending();
        return reviewRepo.findAll(PageRequest.of(page, size, sort)).getContent();
    }

    public List<Review> findByShopName(String shopName) {
        return reviewRepo.findByShopName(shopName);
    }

    public List<Review> findByRatingRange(Double minRating, Double maxRating) {
        return reviewRepo.findByRatingRange(minRating, maxRating);
    }

    public List<Review> findByServiceType(String serviceType) {
        return reviewRepo.findByServiceType(serviceType);
    }
}
