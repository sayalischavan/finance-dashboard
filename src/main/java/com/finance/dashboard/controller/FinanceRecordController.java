package com.finance.dashboard.controller;

import com.finance.dashboard.dto.FinanceRecordDTO;
import com.finance.dashboard.entity.FinanceRecordEntity;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.entity.Status;
import com.finance.dashboard.service.FinanceRecordService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/records")
public class FinanceRecordController {

    private final FinanceRecordService service;

    public FinanceRecordController(FinanceRecordService service) {
        this.service = service;
    }

    //  CREATE RECORD (ADMIN ONLY)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FinanceRecordEntity create(@Valid @RequestBody FinanceRecordDTO dto) {

        FinanceRecordEntity record = new FinanceRecordEntity();
        record.setAmount(dto.amount);
        record.setType(RecordType.valueOf(dto.type));
        record.setCategory(dto.category);
        record.setDate(dto.date);
        record.setNotes(dto.notes);

        return service.create(record);
    }

    //  GET ALL RECORDS (ADMIN + ANALYST + VIEWER)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinanceRecordEntity> getAll() {
        return service.getAll();
    }

    //  GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public FinanceRecordEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //  UPDATE RECORD (ADMIN ONLY)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinanceRecordEntity update(@PathVariable Long id,
                                      @Valid @RequestBody FinanceRecordDTO dto) {

        FinanceRecordEntity record = new FinanceRecordEntity();
        record.setAmount(dto.amount);
        record.setType(RecordType.valueOf(dto.type));
        record.setCategory(dto.category);
        record.setDate(dto.date);
        record.setNotes(dto.notes);

        return service.update(id, record);
    }

    //  DELETE RECORD (ADMIN ONLY)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Record deleted successfully";
    }

    //  FILTER BY TYPE
    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinanceRecordEntity> getByType(@PathVariable String type) {
        return service.getByType(RecordType.valueOf(type));
    }

    // FILTER BY CATEGORY
    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinanceRecordEntity> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // 🔎 FILTER BY DATE RANGE
    @GetMapping("/range")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinanceRecordEntity> getByDateRange(@RequestParam String start,
                                                     @RequestParam String end) {
        return service.getByDateRange(start, end);
    }
}