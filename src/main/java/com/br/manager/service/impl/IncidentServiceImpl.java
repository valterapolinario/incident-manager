package com.br.manager.service.impl;

import com.br.manager.dto.api.request.IncidentReqDTO;
import com.br.manager.dto.api.request.IncidentUpdateReqDTO;
import com.br.manager.dto.api.request.IncidenteFinishReqDTO;
import com.br.manager.dto.api.request.IncidenteReopenReqDTO;
import com.br.manager.dto.api.response.IncidentResDTO;
import com.br.manager.exception.ApiBussinesException;
import com.br.manager.exception.GeneralNotFoundException;
import com.br.manager.mapper.IncidentMapper;
import com.br.manager.model.IncidentDB;
import com.br.manager.model.IncidentStatusEnum;
import com.br.manager.repository.IncidentRepository;
import com.br.manager.service.IncidentService;
import com.br.manager.utils.message.ErrorMessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private IncidentMapper mapper;

    @Override
    public Long createIncident(IncidentReqDTO request) {
        return repository.save(mapper.toEntity(request)).getId();
    }

    @Override
    public void updateIncident(Long id, IncidentUpdateReqDTO request) {
        IncidentDB incidentSaved = repository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));
        incidentSaved = mapper.toModelUpdate(request, incidentSaved);
        incidentSaved.setUpdatedAt(LocalDateTime.now());
        repository.save(incidentSaved);
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
                .map(mapper::toResponse)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));
    }

    @Override
    public List<IncidentResDTO> getAllIncidents() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<IncidentResDTO> getLast20Incidents() {
        return repository
                .findTop20ByOrderByCreatedAtDesc()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void finishIncident(Long id, IncidenteFinishReqDTO finishReqDTO) {
        IncidentDB incidentSaved = repository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));
        if (incidentSaved.getStatus().isCompleted()) {
            throw new ApiBussinesException(ErrorMessagesEnum.INCIDENT_ALREADY_FINISHED.getMessage());
        }
        incidentSaved.setSolutionDescription(finishReqDTO.solution());
        incidentSaved.setStatus(IncidentStatusEnum.CLOSED);
        incidentSaved.setUpdatedAt(LocalDateTime.now());
        incidentSaved.setClosedAt(LocalDateTime.now());
        incidentSaved.setReopenedAt(null);
        repository.save(incidentSaved);
    }

    @Override
    public void reopenIncident(Long id, IncidenteReopenReqDTO reopenReqDTO) {

        IncidentDB incidentSaved = repository.findById(id)
                .orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.INCIDENT_NOT_FOUND.getMessage(), id));

        if (!incidentSaved.getStatus().isCompleted()) {
            throw new ApiBussinesException(ErrorMessagesEnum.INCIDENT_NOT_CLOSED.getMessage());
        }
        incidentSaved.setStatus(IncidentStatusEnum.OPEN);
        incidentSaved.setUpdatedAt(LocalDateTime.now());
        incidentSaved.setReasonForReopening(reopenReqDTO.reasonForReopening());
        incidentSaved.setClosedAt(null);
        incidentSaved.setReopenedAt(LocalDateTime.now());
        repository.save(incidentSaved);
    }

    @Override
    public Page<IncidentResDTO> getPaginedIncidents(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::toResponse);
    }
}
