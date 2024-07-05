package com.br.manager.controller;

import com.br.manager.controller.api.IncidentApi;
import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.request.IncidenteFinishReqDTO;
import com.br.manager.dto.api.request.IncidenteReopenReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import com.br.manager.service.IncidentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/incidents")
@SecurityRequirement(name = "Bearer-key")
public class IncidentController implements IncidentApi {
    public static final String URI_PATH = "/api/v1/incidents/";
    @Autowired
    private IncidentService service;

    @Override
    public ResponseEntity<Void> createIncident(IncidentReqDTO request) throws URISyntaxException {

        Long id = service.createIncident(request);
        URI location = new URI(URI_PATH + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, CREATED);
    }

    @Override
    public ResponseEntity<Void> updateIncident(Long id, IncidentUpdateReqDTO request) throws URISyntaxException {

        service.updateIncident(id, request);
        URI location = new URI(URI_PATH + id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteIncident(Long id) {
        service.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<IncidentResDTO> listById(Long id) {
        return ResponseEntity.ok().body(service.getIncidentById(id));
    }

    @Override
    public ResponseEntity<List<IncidentResDTO>> ListLast20Incidents() {
        return ResponseEntity.ok().body(service.getLast20Incidents());
    }

    @Override
    public ResponseEntity<List<IncidentResDTO>> listAll() {
        return ResponseEntity.ok().body(service.getAllIncidents());
    }

    @Override
    public ResponseEntity<Void> finishIncident(Long id, IncidenteFinishReqDTO finishReqDTO) {
        service.finishIncident(id, finishReqDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> reopenIncident(Long id, IncidenteReopenReqDTO reopenReqDTO) {
        service.reopenIncident(id, reopenReqDTO);
        return null;
    }

    @Override
    public ResponseEntity<Page<IncidentResDTO>> getPagedIncidents(Pageable pageable) {
        return ResponseEntity.ok().body(service.getPaginedIncidents(pageable));
    }
}
