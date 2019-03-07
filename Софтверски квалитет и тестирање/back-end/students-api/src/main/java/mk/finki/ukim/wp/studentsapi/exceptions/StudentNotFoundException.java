package mk.finki.ukim.wp.studentsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String index) {
        super("Studentot so index " + index + " ne e pronajden.");
    }
}
