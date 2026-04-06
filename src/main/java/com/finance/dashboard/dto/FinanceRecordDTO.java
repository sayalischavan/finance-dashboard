package com.finance.dashboard.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FinanceRecordDTO {

    @NotNull
    @Positive
    public Double amount;

    @NotBlank
    public String type;

    @NotBlank
    public String category;

    @NotNull
    public LocalDate date;

    public String notes;
}