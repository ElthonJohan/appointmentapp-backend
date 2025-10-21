package com.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryDTO {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String email;
}
