package fr.ul.mygameslibapirest.exception;

import org.springframework.web.server.ResponseStatusException;

public class FunctionalException extends ResponseStatusException {

    public FunctionalException(ErrorMessage errorMessage, Exception cause, String... additionalInfos) {
        super(errorMessage.getHttpStatus(), errorMessage.getFormattedDescription(additionalInfos), cause);
    }
    public FunctionalException(ErrorMessage errorMessage, String... additionalInfos) {
        super(errorMessage.getHttpStatus(), errorMessage.getFormattedDescription(additionalInfos));
    }

}
