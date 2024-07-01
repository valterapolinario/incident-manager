package com.br.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPositionEnum {
    ADMIN("ADMINISTRADOR"),
    MANAGER("GERENTE"),
    ASSISTANT("ASSISTENTE"),
    OWNER("PROPRIETARIO"),
    OTHER("OUTRO");

    private String description;

    public Boolean isAssistent() {
        return ASSISTANT.equals(this);
    }
}
