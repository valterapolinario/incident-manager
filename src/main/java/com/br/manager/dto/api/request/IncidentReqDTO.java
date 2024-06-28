package com.br.manager.dto.api.request;

import jakarta.validation.constraints.NotBlank;

public record IncidentReqDTO(
        @NotBlank
        String name,

        @NotBlank
        String description

) {
}
