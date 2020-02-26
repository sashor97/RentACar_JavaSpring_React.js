package proektwp.proektwp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "VEKE POSTOI REZERVACIJA ZA TOJ DATUM")
public class ReservationNotValidException extends RuntimeException{
}
