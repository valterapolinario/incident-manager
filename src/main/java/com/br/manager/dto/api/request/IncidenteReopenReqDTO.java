package com.br.manager.dto.api.request;

import jakarta.validation.constraints.NotBlank;

public record IncidenteReopenReqDTO(
        @NotBlank
        String reasonForReopening
) {
}
