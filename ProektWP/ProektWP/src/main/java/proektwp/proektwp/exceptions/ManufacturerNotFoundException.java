package proektwp.proektwp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Proizvoditelot ne postoi so dadenoto ID")
public class ManufacturerNotFoundException extends RuntimeException {
}
