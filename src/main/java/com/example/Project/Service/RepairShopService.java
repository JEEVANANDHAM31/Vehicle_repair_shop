package com.example.Project.Service;

import com.example.Project.Entity.RepairShop;
import com.example.Project.Repository.RepairShopRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RepairShopService {

    @Autowired
    private RepairShopRepo repairShopRepo;

    public List<RepairShop> getAll() {
        return repairShopRepo.findAll();
    }

    public RepairShop getById(Long id) {
        return repairShopRepo.findById(id).orElse(null);
    }

    public List<RepairShop> getByName(String name) {
        return repairShopRepo.findByName(name);
    }

    public List<RepairShop> findByCostRange(int minCost, int maxCost) {
        return repairShopRepo.findByCostRange(minCost, maxCost);
    }

    public List<RepairShop> paginate(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return repairShopRepo.findAll(PageRequest.of(page, size, Sort.by(sortDirection, sortBy))).getContent();
    }

    public String addRepairShop(RepairShop repairShop) {
        if (repairShopRepo.findByEmail(repairShop.getEmail()).isPresent()) {
            return "Email already exists";
        }
        if (repairShopRepo.findByPhoneNumber(repairShop.getPhoneNumber()).isPresent()) {
            return "Phone number already exists";
        }
        repairShopRepo.save(repairShop);
        return "Repair shop successfully added";
    }

    public String updateRepairShop(Long id, RepairShop updatedRepairShop) {
        Optional<RepairShop> existingRepairShopOpt = repairShopRepo.findById(id);
        if (existingRepairShopOpt.isEmpty()) {
            return "Repair shop not found with ID: " + id;
        }

        RepairShop existingRepairShop = existingRepairShopOpt.get();

        // Check for duplicate email (excluding current repair shop)
        if (repairShopRepo.findByEmail(updatedRepairShop.getEmail()).isPresent() &&
            !existingRepairShop.getEmail().equals(updatedRepairShop.getEmail())) {
            return "Email already exists";
        }

        // Check for duplicate phone number (excluding current repair shop)
        if (repairShopRepo.findByPhoneNumber(updatedRepairShop.getPhoneNumber()).isPresent() &&
            !existingRepairShop.getPhoneNumber().equals(updatedRepairShop.getPhoneNumber())) {
            return "Phone number already exists";
        }

        // Copy properties except ID
        BeanUtils.copyProperties(updatedRepairShop, existingRepairShop, "id");

        repairShopRepo.save(existingRepairShop);
        return "Repair shop updated successfully";
    }

    public String deleteById(Long id) {
        if (!repairShopRepo.existsById(id)) {
            return "Repair shop not found with ID: " + id;
        }
        repairShopRepo.deleteById(id);
        return "Deleted successfully";
    }

    public String deleteAll() {
        repairShopRepo.deleteAll();
        return "Deleted all repair shops";
    }

    public List<RepairShop> paginationBySort(int page, int size, String sortBy, String sortDir) {
        Sort.Direction sortDirection = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return repairShopRepo.findAll(PageRequest.of(page, size, Sort.by(sortDirection, sortBy))).getContent();
    }

    public String deleteRecords(long id) {
        if (!repairShopRepo.existsById(id)) {
            return "Repair shop not found with ID: " + id;
        }
        repairShopRepo.deleteById(id);
        return "Record deleted successfully";
    }

    @Transactional(readOnly = true)
    public RepairShop getRepairShopWithAppointments(Long repairShopId) {
        RepairShop repairShop = repairShopRepo.findById(repairShopId)
                .orElseThrow(() -> new RuntimeException("RepairShop not found"));
        repairShop.getAppointments().size(); // Initialize lazy-loaded collection
        return repairShop;
    }

    @Transactional(readOnly = true)
    public RepairShop getRepairShopWithReviews(Long repairShopId) {
        RepairShop repairShop = repairShopRepo.findById(repairShopId)
                .orElseThrow(() -> new RuntimeException("RepairShop not found"));
        repairShop.getReviews().size(); // Initialize lazy-loaded collection
        return repairShop;
    }
}
