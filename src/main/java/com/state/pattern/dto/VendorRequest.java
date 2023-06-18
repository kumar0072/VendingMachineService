package com.state.pattern.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {

    @NotNull
    int id;
    @NotNull
    int quantity;
}
