package com.api.expenseservice.controller;

import com.api.expenseservice.model.request.ExpenseRequest;
import com.api.expenseservice.service.ExpensesService;
import com.api.expenseservice.util.APIResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/expenses")
//@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ExpensesController {
    private final ExpensesService expensesService;

    @GetMapping("/getAllExpenses")
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(expensesService.getAllExpenses(), HttpStatus.OK)
        );
    }

    @GetMapping("/getExpenseById/{expenseId}")
    public ResponseEntity<?> getExpenseById(@PathVariable Integer expenseId) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(expensesService.getExpenseById(expenseId), HttpStatus.OK)
        );
    }

    @PostMapping("/addNewExpense")
    public ResponseEntity<?> addNewExpense(@RequestBody ExpenseRequest expenseRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        expensesService.addNewExpense(expenseRequest),
                        HttpStatus.CREATED
                )
        );
    }

    @PutMapping("/updateExpenseById/{expenseId}")
    public ResponseEntity<?> updateExpenseById(@PathVariable Integer expenseId, @RequestBody ExpenseRequest expenseRequest) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        expensesService.updateExpenseById(expenseId,
                                expenseRequest), HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/deleteExpenseById/{expenseId}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable Integer expenseId) {
        return ResponseEntity.ok(
                APIResponseUtil.apiResponse(
                        expensesService.deleteExpenseById(expenseId),
                        HttpStatus.OK
                )
        );
    }
}
