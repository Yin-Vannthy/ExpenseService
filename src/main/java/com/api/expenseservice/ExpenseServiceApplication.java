package com.api.expenseservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Expense Tracking",
        version = "v1",
        description = "Expense Tracking API provides endpoints for managing and tracking bug efficiently. It offers features for user authentication, bug management. The API follows the OpenAPI specification, allowing easy integration with other systems."
))
public class ExpenseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseServiceApplication.class, args);
    }

}
