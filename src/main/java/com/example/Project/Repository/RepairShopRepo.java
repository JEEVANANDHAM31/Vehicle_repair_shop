package com.example.Project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Project.Entity.RepairShop;

@Repository
public interface RepairShopRepo extends JpaRepository<RepairShop, Long> {

    List<RepairShop> findByName(String name);

    @Query("SELECT r FROM RepairShop r WHERE r.cost BETWEEN :minCost AND :maxCost")
    List<RepairShop> findByCostRange(@Param("minCost") int minCost, @Param("maxCost") int maxCost);

    Optional<RepairShop> findByEmail(String email);  // ✅ Fixed type (Object → String)

    Optional<RepairShop> findByPhoneNumber(String phoneNumber);  // ✅ Fixed type (Object → String)
}
