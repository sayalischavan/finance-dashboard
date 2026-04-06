package com.finance.dashboard.service;
import com.finance.dashboard.entity.FinanceRecordEntity;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.repository.FinanceRecordRepository;
import com.finance.dashboard.service.DashboardService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final FinanceRecordRepository repository;

    public DashboardService(FinanceRecordRepository repository) {
        this.repository = repository;
    }

    // ---------------- SUMMARY ----------------

    public Map<String, Double> getSummary() {
        Map<String, Double> map = new HashMap<>();
        map.put("income", getTotalIncome());
        map.put("expense", getTotalExpense());
        map.put("netBalance", getNetBalance());
        return map;
    }

    // ---------------- TOTAL INCOME ----------------
    
    public Double getTotalIncome() {
        return repository.findAll()
                .stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinanceRecordEntity::getAmount)
                .sum();
    }

    // ---------------- TOTAL EXPENSE ----------------
  
    public Double getTotalExpense() {
        return repository.findAll()
                .stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinanceRecordEntity::getAmount)
                .sum();
    }

    // ---------------- NET BALANCE ----------------
  
    public Double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    // ---------------- CATEGORY WISE TOTAL ----------------
   
    public Map<String, Double> getCategoryWiseTotal() {

        return repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        FinanceRecordEntity::getCategory,
                        Collectors.summingDouble(FinanceRecordEntity::getAmount)
                ));
    }

    // ---------------- MONTHLY TREND ----------------
    
    public Map<String, Double> getMonthlyTrend() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        return repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        r -> r.getDate().format(formatter),
                        Collectors.summingDouble(FinanceRecordEntity::getAmount)
                ));
    }
}