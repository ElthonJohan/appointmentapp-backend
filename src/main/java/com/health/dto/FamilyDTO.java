package com.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDTO {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;
}
