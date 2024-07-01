package com.br.manager.controller.api;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.request.IncidenteFinishReqDTO;
import com.br.manager.dto.api.request.IncidenteReopenReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Incidentes")
public interface IncidentApi {
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create a new incident", description = "Create Incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", headers = {
                    @Header(name = "Location", description = "The URI to retrieve the created resource", schema = @Schema(type = "string", implementation = URI.class, example = "api/v1/1"))
            },
                    content = {
                            @Content(mediaType = "application/json")
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<Void> createIncident(@Parameter(description = "Incident to create.", required = true, schema = @Schema(implementation = IncidentReqDTO.class)) @RequestBody @Valid IncidentReqDTO request) throws URISyntaxException;

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Update an existing incident", description = "Update Incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<Void> updateIncident(@Parameter(description = "Incident ID to update.", required = true) @PathVariable Long id,
                                        @Parameter(description = "Incident to update.", required = true, schema = @Schema(implementation = IncidentUpdateReqDTO.class))
                                        @RequestBody @Valid IncidentUpdateReqDTO request) throws URISyntaxException;

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Delete an existing incident", description = "Delete Incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<Void> deleteIncident(@Parameter(description = "Incident ID to delete.", required = true) @PathVariable Long id);

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Get an existing incident", description = "Get Incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = IncidentResDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<IncidentResDTO> listById(@Parameter(description = " Incident ID to retrieve.", required = true) @PathVariable Long id);

    @GetMapping("/last-incidents")
    @ResponseStatus(OK)
    @Operation(summary = "Get the last twenty incidents", description = "Get the last twenty incidents in descending order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IncidentResDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<List<IncidentResDTO>> ListLast20Incidents();

    @GetMapping
    @ResponseStatus(OK)
    @Operation(summary = "Get all incidents", description = "Get all incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IncidentResDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<List<IncidentResDTO>> listAll();

    @PatchMapping("/finish-incidente/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Finish an existing incident", description = "ends existing incident by informing the description of the solution executed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<Void> finishIncident(@Parameter(description = "Incident ID to finish.", required = true) @PathVariable Long id,
                                        @Parameter(description = "information for finalizing an incident   ", required = true) @RequestBody @Valid IncidenteFinishReqDTO finishReqDTO);

    @PatchMapping("/reopen-incidente/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Reopen an existing incident", description = "reopens an existing incident stating the reason for reopening")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            })})
    ResponseEntity<Void> reopenIncident(@Parameter(description = "Incident ID to reopen.", required = true) @PathVariable Long id,
                                        @Parameter(description = " information for reopening an incident", required = true) @RequestBody @Valid IncidenteReopenReqDTO reopenReqDTO);

    @GetMapping("/paged")
    @ResponseStatus(OK)
    @Operation(summary = "Get paginated incidents", description = "search incidents in paginated form, by default bringing up the last twenty records in descending order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Page<IncidentResDTO>> getPagedIncidents(
            @Parameter(description = "Pageable object", schema = @Schema(implementation = Pageable.class)) @PageableDefault(size = 20, direction = Sort.Direction.DESC, sort = "createdAt") Pageable pageable);

}
