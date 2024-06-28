package com.br.manager.utils.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessagesEnum {

    INCIDENT_NOT_FOUND("Incident not found"),
    INCIDENT_ALREADY_EXISTS("Incident already exists");

    private final String message;
}
