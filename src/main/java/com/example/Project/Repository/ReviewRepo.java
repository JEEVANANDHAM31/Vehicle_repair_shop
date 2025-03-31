package com.example.Project.Repository;

import com.example.Project.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByShopName(String shopName);

    @Query("SELECT r FROM Review r WHERE r.rating BETWEEN :minRating AND :maxRating")
    List<Review> findByRatingRange(@Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

    List<Review> findByServiceType(String serviceType);
}
