package com.example.Project.Entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data  
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private LocalDate appointmentDate;
    private String serviceRequested;
    private String status;

    // Many-to-One: Appointment → User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-appointments")
    private User user;

    // Many-to-One: Appointment → RepairShop
    @ManyToOne
    @JoinColumn(name = "repair_shop_id", nullable = false)
    @JsonBackReference(value = "repairshop-appointments")
    private RepairShop repairShop;

    public void setUser(User user) {
        this.user = user;
    }

    public void setRepairShop(RepairShop repairShop) {
        this.repairShop = repairShop;
    }
}
