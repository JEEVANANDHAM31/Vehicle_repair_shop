package com.example.Project.Service;

import com.example.Project.Entity.Appointment;
import com.example.Project.Entity.RepairShop;
import com.example.Project.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();
        if (appointments.isEmpty()) {
            throw new RuntimeException("No appointments found");
        }

        // Validate that all associated RepairShops have valid IDs
        for (Appointment appointment : appointments) {
            RepairShop repairShop = appointment.getRepairShop();
            if (repairShop == null || repairShop.getId() == null || repairShop.getId() <= 0) {
                throw new RuntimeException("Invalid RepairShop ID associated with an appointment");
            }
        }

        return appointments;
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepo.findById(id).orElse(null);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepo.findById(id).orElse(null);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setServiceRequested(appointment.getServiceRequested());
            existingAppointment.setStatus(appointment.getStatus());
            return appointmentRepo.save(existingAppointment);
        }
        return null;
    }

    public void deleteAppointment(Long id) {
        appointmentRepo.deleteById(id);
    }
}
