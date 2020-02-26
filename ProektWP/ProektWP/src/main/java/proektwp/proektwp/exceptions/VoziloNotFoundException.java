package proektwp.proektwp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Voziloto so dadenoto ID ne e pronajdeno!!!")
public class VoziloNotFoundException extends RuntimeException {
}
