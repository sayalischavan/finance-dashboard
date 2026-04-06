package com.finance.dashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.dashboard.entity.FinanceRecordEntity;
import com.finance.dashboard.entity.RecordType;



public interface FinanceRecordRepository extends JpaRepository<FinanceRecordEntity,Long>{

	List<FinanceRecordEntity> findByType(RecordType type);

	List<FinanceRecordEntity> findByCategory(String category);

	List<FinanceRecordEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
