package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreatePoFromReorderSuggestionsRequest {

    @NotNull
    private Long supplierId;

    /** When null or empty , include every current low-stock suggestions. */
    private List<Long> itemIds;

}
