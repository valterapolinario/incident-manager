package com.br.manager.service.impl;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import com.br.manager.exception.GeneralNotFoundException;
import com.br.manager.model.IncidentDB;
import com.br.manager.repository.IncidentRepository;
import com.br.manager.service.IncidentService;
import com.br.manager.utils.message.ErrorMessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository repository;

    @Override
    public Long createIncident(IncidentReqDTO request) {
        return repository.save(toModel(request)).getId();
    }

    @Override
    public void updateIncident(Long id, IncidentUpdateReqDTO request) {
        IncidentDB incidentSaved = repository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));
        IncidentDB incident = toModelUpdate(request);
        incident.setId(incidentSaved.getId());
        incident.setCreatedAt(incidentSaved.getCreatedAt());
        incident.setUpdatedAt(LocalDateTime.now());
        repository.save(incident);
    }

    @Override
    public void deleteIncident(Long id) {
        if (!repository.existsById(id)) {
            throw new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id);
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public IncidentResDTO getIncidentById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));
    }

    @Override
    public List<IncidentResDTO> getAllIncidents() {
        return repository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<IncidentResDTO> getLast20Incidents() {
        return repository
                .findTop20ByOrderByCreatedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private IncidentDB toModel(IncidentReqDTO request) {
        return IncidentDB
                .builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    private IncidentResDTO toResponse(IncidentDB model) {
        return new IncidentResDTO(
                model.getName(),
                model.getDescription(),
                model.getCreatedAt(),
                model.getUpdatedAt(),
                model.getClosedAt()
        );
    }

    private IncidentDB toModelUpdate(IncidentUpdateReqDTO request) {
        return IncidentDB
                .builder()
                .name(request.name())
                .description(request.description())
                .build();
    }
}
