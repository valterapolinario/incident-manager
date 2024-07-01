package com.br.manager.service;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.request.IncidenteFinishReqDTO;
import com.br.manager.dto.api.request.IncidenteReopenReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncidentService {

    Long createIncident(IncidentReqDTO request);

    void updateIncident(Long id, IncidentUpdateReqDTO request);

    void deleteIncident(Long id);

    IncidentResDTO getIncidentById(Long id);

    List<IncidentResDTO> getAllIncidents();

    List<IncidentResDTO> getLast20Incidents();

    void finishIncident(Long id, IncidenteFinishReqDTO finishReqDTO);

    void reopenIncident(Long id, IncidenteReopenReqDTO reopenReqDTO);

    Page<IncidentResDTO> getPaginedIncidents(Pageable pageable);
}
