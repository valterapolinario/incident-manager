package com.br.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IncidentStatusEnum {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    CLOSED("Closed");

    private final String value;

    public boolean isCompleted() {
        return CLOSED.equals(this);
    }
}
