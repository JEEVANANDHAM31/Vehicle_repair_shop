package com.example.Project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double rating;
    private String comment;
    
    @Column(name = "shop_name")
    private String shopName;
    
    @Column(name = "service_type")
    private String serviceType;
    
    @Column(name = "appointment_id")
    private Long appointmentId;
    
    private LocalDate date;

    // Many-to-One: Review → User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-reviews")
    private User user;

    // Many-to-One: Review → RepairShop
    @ManyToOne
    @JoinColumn(name = "repair_shop_id", nullable = false)
    @JsonBackReference(value = "repairshop-reviews")
    private RepairShop repairShop;
}
