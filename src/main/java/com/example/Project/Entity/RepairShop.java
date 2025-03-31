package com.example.Project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Data
@Table(name = "repair_shop", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "location"})})
@JsonIgnoreProperties({"appointments", "reviews"}) // Ignore lazy-loaded collections during serialization
public class RepairShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String cost;
    private String location;
    private String services;
    private String address;
    private Double rating;
    private String maintenanceTips;
    private String email;  
    private String phoneNumber; 

    // One-to-Many: RepairShop → Appointments
    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "repairshop-appointments")
    private List<Appointment> appointments;

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                appointment.setRepairShop(this); // Ensure bidirectional relationship
            }
        }
    }

    // One-to-Many: RepairShop → Reviews
    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "repairshop-reviews")
    private List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        if (reviews != null) {
            for (Review review : reviews) {
                review.setRepairShop(this); // Ensure bidirectional relationship
            }
        }
    }
}
