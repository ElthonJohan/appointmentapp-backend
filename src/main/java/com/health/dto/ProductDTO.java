package com.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer idProduct;
    @NotNull
    private String name;
    private String description;
    private String presentation;
    @NotNull
    private Double unitPrice;
    @NotNull
    private Integer stock;

    private LocalDate expired;
    @NotNull(message = "Debe seleccionar una categoría")
    private Long categoryId;

    @NotNull(message = "Debe seleccionar una familia")
    private Long familyId;

    @NotNull(message = "Debe seleccionar un laboratorio")
    private Long laboratoryId;
}
