package com.finance.dashboard.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finance.dashboard.entity.FinanceRecordEntity;
import com.finance.dashboard.entity.RecordType;
import com.finance.dashboard.repository.FinanceRecordRepository;

@Service
public class FinanceRecordService {

	private  FinanceRecordRepository financeRepo;

	public FinanceRecordService(FinanceRecordRepository financeRepo) {
		super();
		this.financeRepo = financeRepo;
	}
	
	
	public FinanceRecordEntity create(FinanceRecordEntity financeRecord)
	{
		return financeRepo.save(financeRecord);
	}
	
	 public List<FinanceRecordEntity> getAll() {
	        return financeRepo.findAll();
	    }

	 public FinanceRecordEntity update(Long id, FinanceRecordEntity record) {
	        FinanceRecordEntity existing = getById(id);

	        existing.setAmount(record.getAmount());
	        existing.setType(record.getType());
	        existing.setCategory(record.getCategory());
	        existing.setDate(record.getDate());
	        existing.setNotes(record.getNotes());

	        return financeRepo.save(existing);
	    }

    public void delete(Long id) {
    	financeRepo.deleteById(id);
    }


    public FinanceRecordEntity getById(Long id) {
        return financeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<FinanceRecordEntity> getByType(RecordType type) {
        return financeRepo.findByType(type);
    }


    public List<FinanceRecordEntity> getByCategory(String category) {
        return financeRepo.findByCategory(category);
    }

    public List<FinanceRecordEntity> getByDateRange(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return financeRepo.findByDateBetween(startDate, endDate);
    }
}
