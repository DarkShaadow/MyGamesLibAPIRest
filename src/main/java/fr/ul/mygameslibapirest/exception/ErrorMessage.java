package fr.ul.mygameslibapirest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorMessage extends Throwable {

    private final HttpStatus httpStatus;
    private final String description;

    public String getFormattedDescription(String... additionalInfos) {
        String formatDescription;
        List<String> infos;

        infos = new ArrayList<>();
        if (additionalInfos != null) {
            infos.addAll(List.of(additionalInfos));
        }

        try {
            formatDescription = String.format(this.description, infos.toArray());
        }
        catch (IllegalFormatException illegalFormatException) {
            formatDescription = this.description;
        }

        return formatDescription;
    }
}
