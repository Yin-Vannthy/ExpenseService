package com.api.expenseservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseRequest {
    private Double amount;
    private String description;
    private Date date;
    private Integer userId;
    private Integer categoryId;
}
