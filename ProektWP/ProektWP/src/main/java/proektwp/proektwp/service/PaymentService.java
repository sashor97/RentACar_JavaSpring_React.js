package proektwp.proektwp.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import proektwp.proektwp.dto.ChargeRequest;
import proektwp.proektwp.models.Transactions;

public interface PaymentService {
    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException;
    Transactions saveTransaction (Transactions t);
}
