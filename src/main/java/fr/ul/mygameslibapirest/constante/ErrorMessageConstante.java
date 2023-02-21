package fr.ul.mygameslibapirest.constante;

import fr.ul.mygameslibapirest.exception.ErrorMessage;
import org.springframework.http.HttpStatus;

public class ErrorMessageConstante {

    public static final ErrorMessage MISSING_RESOURCE = new ErrorMessage(HttpStatus.NOT_FOUND, "%s d'identifiant %s n'existe pas");

    private ErrorMessageConstante() {
    }
}
