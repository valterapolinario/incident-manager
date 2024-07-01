package com.br.manager.utils.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessagesEnum {

    INCIDENT_NOT_FOUND("Incident not found"),
    INCIDENT_ALREADY_EXISTS("Incident already exists"),
    INCIDENT_ALREADY_FINISHED("Incident already finished"),
    SOLUTION_NOT_INFORMED("Solution not informed"),
    INCIDENT_NOT_CLOSED(" Incident has not been finalized"),
    LOGIN_INCORRECT("login information not found");
    private final String message;
}
