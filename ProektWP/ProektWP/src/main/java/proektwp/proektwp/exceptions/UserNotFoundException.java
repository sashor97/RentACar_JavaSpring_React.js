package proektwp.proektwp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Korinikot so dadenoto ID ne postoi")
public class UserNotFoundException extends RuntimeException {
}
