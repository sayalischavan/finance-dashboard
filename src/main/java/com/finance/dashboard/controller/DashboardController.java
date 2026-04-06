package com.finance.dashboard.controller;

import com.finance.dashboard.entity.Role;
import com.finance.dashboard.security.ValidateRole;
import com.finance.dashboard.service.DashboardService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public Map<String, Double> summary(@RequestHeader Role role) {
    	ValidateRole.checkAccess(role, Role.VIEWER, Role.ANALYST, Role.ADMIN);

        Map<String, Double> data = new HashMap<>();
        data.put("income", service.getTotalIncome());
        data.put("expense", service.getTotalExpense());
        data.put("netBalance", service.getNetBalance());

        return data;
    }
    
    @GetMapping("/total-income")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Double getTotalIncome() {
        return service.getTotalIncome();
    }
    
    @GetMapping("/total-expense")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Double getTotalExpense() {
        return service.getTotalExpense();
    }
    
    @GetMapping("/net-balance")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Double getNetBalance() {
        return service.getNetBalance();
    }
    
    @GetMapping("/category-wise")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Map<String, Double> categoryWise() {
        return service.getCategoryWiseTotal();
    }
    
    @GetMapping("/monthly-trend")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    public Map<String, Double> monthlyTrend() {
        return service.getMonthlyTrend();
    }
}