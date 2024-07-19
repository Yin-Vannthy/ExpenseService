package com.api.expenseservice.service;


import com.api.expenseservice.model.dto.ExpensesDTO;
import com.api.expenseservice.model.entity.Expenses;
import com.api.expenseservice.model.request.ExpenseRequest;

import java.util.List;

public interface ExpensesService {
    List<ExpensesDTO> getAllExpenses();

    ExpensesDTO getExpenseById(Integer expenseId);

    Expenses addNewExpense(ExpenseRequest expenseRequest);

    Expenses updateExpenseById(Integer expenseId, ExpenseRequest expenseRequest);

    Boolean deleteExpenseById(Integer expenseId);
}
