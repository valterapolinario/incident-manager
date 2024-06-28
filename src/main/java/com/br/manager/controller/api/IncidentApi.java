package com.br.manager.controller.api;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface IncidentApi {

    ResponseEntity<Void> createIncident(IncidentReqDTO request) throws URISyntaxException;

    ResponseEntity<Void> updateIncident(Long id, IncidentUpdateReqDTO request) throws URISyntaxException;

    ResponseEntity<Void> deleteIncident(Long id);

    ResponseEntity<IncidentResDTO> listById(Long id);

    ResponseEntity<List<IncidentResDTO>> ListLast20Incidents();

    ResponseEntity<List<IncidentResDTO>> listAll();


}
