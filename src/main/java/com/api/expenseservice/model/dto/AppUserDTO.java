package com.api.expenseservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUserDTO {
    private Integer userId;
    private String email;
    private String profileImage;
    private Boolean isVerify;
}