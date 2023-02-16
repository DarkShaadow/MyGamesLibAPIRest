package fr.ul.mygameslibapirest.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessageConstante {

    public static final ErrorMessage MISSING_RESOURCE = new ErrorMessage(HttpStatus.NOT_FOUND, "%s d'identifiant %s n'existe pas");

    private ErrorMessageConstante() {
    }
}
