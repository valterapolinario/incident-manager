package com.br.manager.service;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;

import java.util.List;

public interface IncidentService {

    Long createIncident(IncidentReqDTO request);

    void updateIncident(Long id, IncidentUpdateReqDTO request);

    void deleteIncident(Long id);

    IncidentResDTO getIncidentById(Long id);

    List<IncidentResDTO> getAllIncidents();

    List<IncidentResDTO> getLast20Incidents();
}
