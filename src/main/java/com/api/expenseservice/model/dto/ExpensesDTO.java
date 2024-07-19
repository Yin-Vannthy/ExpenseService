package com.api.expenseservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpensesDTO {
    private Integer expenseId;
    private Double amount;
    private String description;
    private Date date;
    private CategoriesDTO category;
    private AppUserDTO user;
}
