package com.api.expenseservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoriesDTO {
    private Integer categoryId;
    private String name;
    private String description;
    private AppUserDTO user;
}
