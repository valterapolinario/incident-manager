package com.br.manager.exception;

import lombok.Getter;

@Getter
public class ApiBussinesException extends RuntimeException {
    public ApiBussinesException(String message) {
        super(message);
    }

}
