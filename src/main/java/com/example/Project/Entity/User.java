package com.example.Project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email"),
    @UniqueConstraint(columnNames = "phone")
})
@JsonIgnoreProperties({"appointments", "reviews"}) // Ignore lazy-loaded fields
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    @Column(unique = true, nullable = false)
    private long phone;

    private String address;

    // One-to-Many: User → Appointments
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-appointments")
    private List<Appointment> appointments;

    // One-to-Many: User → Reviews
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-reviews")
    private List<Review> reviews;

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                appointment.setUser(this); // Ensure bidirectional relationship
            }
        }
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        if (reviews != null) {
            for (Review review : reviews) {
                review.setUser(this); // Ensure bidirectional relationship
            }
        }
    }
}