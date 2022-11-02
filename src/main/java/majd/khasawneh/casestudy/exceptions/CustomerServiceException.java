package majd.khasawneh.casestudy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerServiceException extends RuntimeException {
    public CustomerServiceException(String message) {
        super(message);
    }
}
