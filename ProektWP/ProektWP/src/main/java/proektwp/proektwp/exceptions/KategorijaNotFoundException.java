package proektwp.proektwp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Ne postoi kategorija so dadenoto ID")
public class KategorijaNotFoundException extends RuntimeException{
}
