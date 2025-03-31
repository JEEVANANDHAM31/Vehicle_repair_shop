package com.example.Project.Controller;

import org.springframework.web.bind.annotation.*;
import com.example.Project.Service.RepairShopService;
import com.example.Project.Entity.RepairShop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/repairshop")
public class RepairShopController {

    @Autowired
    private RepairShopService repairService;

    // Get all repair shops
    @GetMapping("/get")
    public List<RepairShop> getAll() {
        return repairService.getAll();
    }

    // Add a new repair shop
    @PostMapping("/post")
    public String addRepairShop(@RequestBody RepairShop repair) {
        repairService.addRepairShop(repair);
        return "Successfully added";
    }

    // Get a repair shop by ID
    @GetMapping("/get/{id}")
    public RepairShop getById(@PathVariable long id) {
        return repairService.getById(id);
    }

    // Get repair shops by name
    @GetMapping("/get/name")
    public List<RepairShop> getByName(@RequestParam String name) {
        return repairService.getByName(name);
    }

    // Update repair shop details
    @PutMapping("/update/{id}")
    public String updateRepairShop(@PathVariable long id, @RequestBody RepairShop repair) {
        return repairService.updateRepairShop(id, repair);
    }

    // Delete a repair shop by ID
    @DeleteMapping("/delete/{id}")
    public String deleteRecords(@PathVariable long id) {
        repairService.deleteRecords(id);
        return "Deleted Successfully";
    }

    // Get paginated and sorted repair shops
    @GetMapping("/paginationbysort")
    public List<RepairShop> paginationBySort(
            @RequestParam int page, 
            @RequestParam int size, 
            @RequestParam String sortBy, 
            @RequestParam(defaultValue = "asc") String sortDir) {
        return repairService.paginationBySort(page, size, sortBy, sortDir);
    }

    // Get repair shops by cost range
    @GetMapping("/getByCostRange")
    public List<RepairShop> getRepairShopsByCostRange(
            @RequestParam(defaultValue = "2000") int minCost, 
            @RequestParam(defaultValue = "10000") int maxCost) {
        return repairService.findByCostRange(minCost, maxCost);
    }

    // Delete all repair shops
    @DeleteMapping("/deleteall")
    public String deleteAllRecords() {
        repairService.deleteAll();
        return "All records deleted successfully";
    }
}
