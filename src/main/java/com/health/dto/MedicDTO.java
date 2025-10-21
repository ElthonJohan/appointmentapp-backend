package com.health.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicDTO {
    private Integer idMedic;

    @NotNull
    private Integer idSpecialty;

    @NotNull
    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 12)
    private String cmp;

    @NotNull
    private String photo;
}
