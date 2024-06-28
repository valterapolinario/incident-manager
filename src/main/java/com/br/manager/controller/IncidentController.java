package com.br.manager.controller;

import com.br.manager.controller.api.IncidentApi;
import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import com.br.manager.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController implements IncidentApi {

    @Autowired
    private IncidentService service;

    @Override
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Void> createIncident(@RequestBody @Valid IncidentReqDTO request) throws URISyntaxException {

        Long id = service.createIncident(request);
        URI location = new URI("/api/v1/incidents/" + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> updateIncident(@PathVariable Long id, @RequestBody IncidentUpdateReqDTO request) throws URISyntaxException {
        service.updateIncident(id, request);
        URI location = new URI("/api/v1/incidents/" + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, NO_CONTENT);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        service.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<IncidentResDTO> listById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getIncidentById(id));
    }

    @Override
    @GetMapping("/last-incidents")
    @ResponseStatus(OK)
    public ResponseEntity<List<IncidentResDTO>> ListLast20Incidents() {
        return ResponseEntity.ok().body(service.getLast20Incidents());
    }

    @Override
    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<List<IncidentResDTO>> listAll() {
        return ResponseEntity.ok().body(service.getAllIncidents());
    }
}
