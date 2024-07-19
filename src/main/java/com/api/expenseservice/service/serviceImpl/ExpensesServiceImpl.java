package com.api.expenseservice.service.serviceImpl;

import com.api.expenseservice.exception.CustomNotFoundException;
import com.api.expenseservice.model.dto.AppUserDTO;
import com.api.expenseservice.model.dto.CategoriesDTO;
import com.api.expenseservice.model.dto.ExpensesDTO;
import com.api.expenseservice.model.entity.Expenses;
import com.api.expenseservice.model.request.ExpenseRequest;
import com.api.expenseservice.repository.ExpensesRepository;
import com.api.expenseservice.response.ApiResponse;
import com.api.expenseservice.service.ExpensesService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpensesServiceImpl implements ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<ExpensesDTO> getAllExpenses() {
        return expensesRepository.findAll()
                .stream()
                .map(this::setCategoryDetails)
                .collect(Collectors.toList());
    }

    @Override
    public ExpensesDTO getExpenseById(Integer expenseId) {
        Expenses expenses = expensesRepository.findById(expenseId).orElseThrow(
                () -> new CustomNotFoundException("Expense not found with id: " + expenseId)
        );
        return setCategoryDetails(expenses);
    }

    @Override
    public Expenses addNewExpense(ExpenseRequest expenseRequest) {
        getCategoryDetails(expenseRequest.getCategoryId());
        getUserDetails(expenseRequest.getUserId());

        Expenses expenses = Expenses.builder()
                .amount(expenseRequest.getAmount())
                .description(expenseRequest.getDescription())
                .date(expenseRequest.getDate())
                .categoryId(expenseRequest.getCategoryId())
                .userId(expenseRequest.getUserId())
                .build();

        return expensesRepository.save(expenses);
    }

    @Override
    public Expenses updateExpenseById(Integer expenseId, ExpenseRequest expenseRequest) {
        getCategoryDetails(expenseRequest.getCategoryId());
        getUserDetails(expenseRequest.getUserId());
        getExpenseById(expenseId);

        Expenses expenses = Expenses.builder()
                .expenseId(expenseId)
                .amount(expenseRequest.getAmount())
                .description(expenseRequest.getDescription())
                .date(expenseRequest.getDate())
                .categoryId(expenseRequest.getCategoryId())
                .userId(expenseRequest.getUserId())
                .build();

        return expensesRepository.save(expenses);
    }

    @Override
    public Boolean deleteExpenseById(Integer expenseId) {
        getExpenseById(expenseId);
        expensesRepository.deleteById(expenseId);
        return true;
    }

    private ExpensesDTO setCategoryDetails(Expenses expenses) {
        CategoriesDTO categoriesDTO = getCategoryDetails(expenses.getCategoryId());
        AppUserDTO appUserDTO = getUserDetails(expenses.getUserId());

        return ExpensesDTO.builder()
                .expenseId(expenses.getExpenseId())
                .amount(expenses.getAmount())
                .description(expenses.getDescription())
                .date(expenses.getDate())
                .category(categoriesDTO)
                .user(appUserDTO)
                .build();
    }

    private CategoriesDTO getCategoryDetails(Integer categoryId) {
        try {
            return Objects.requireNonNull(
                    restTemplate.exchange(
                            "http://localhost:8081/api/v1/categories/getCategoryById/" + categoryId,
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            new ParameterizedTypeReference<ApiResponse<CategoriesDTO>>() {}
                    ).getBody()
            ).getPayload();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CustomNotFoundException("Category not found with id: " + categoryId);
        }
    }

    private AppUserDTO getUserDetails(Integer userId) {
        try{
            return Objects.requireNonNull(
                    restTemplate.exchange(
                            "http://localhost:8080/api/v1/authentication/getUserById/" + userId,
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            new ParameterizedTypeReference<ApiResponse<AppUserDTO>>() {}
                    ).getBody()
            ).getPayload();
        }catch (HttpClientErrorException.NotFound e){
            throw new CustomNotFoundException("No user found with id: " + userId);
        }
    }
}
