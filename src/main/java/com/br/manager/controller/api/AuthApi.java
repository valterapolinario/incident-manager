package com.br.manager.controller.api;

import com.br.manager.dto.auth.AuthRequestDTO;
import com.br.manager.dto.auth.AuthResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Authentication", description = "Operations for user authentication")
public interface AuthApi {

    @PostMapping(value = "/login")
    @ResponseStatus(CREATED)
    @Operation(summary = "Login to obtain JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDTO.class))
    })
    @ApiResponse(responseCode = "400", description = "Invalid input or credentials", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
    })
    ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request);
}
