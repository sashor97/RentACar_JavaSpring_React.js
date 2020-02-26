package proektwp.proektwp.web.Controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proektwp.proektwp.dto.ChargeRequest;
import proektwp.proektwp.exceptions.RezervacijaNotFoundException;
import proektwp.proektwp.models.Rezervacija;
import proektwp.proektwp.models.Transactions;
import proektwp.proektwp.service.PaymentService;
import proektwp.proektwp.service.RezervacijaService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private PaymentService paymentService;

    private RezervacijaService rezervacijaService;

    public PaymentController(PaymentService paymentService, RezervacijaService rezervacijaService) {
        this.paymentService = paymentService;
        this.rezervacijaService = rezervacijaService;
    }

    @RequestMapping("/checkout/")
    public String checkout(HttpServletRequest request, Model model) {
        Long id = Long.parseLong(request.getParameter("rezervacijaId"));
        Rezervacija rezervacija = rezervacijaService.findById(id).orElseThrow(() -> new RezervacijaNotFoundException());
        rezervacijaService.plati(rezervacija);
        model.addAttribute("proizvoditel", rezervacija.getVozilo().getProizvoditel().getName());
        model.addAttribute("name", rezervacija.getVozilo().getModel());
        model.addAttribute("datumOd", rezervacija.getDatumOd());
        model.addAttribute("datumDo", rezervacija.getDatumDo());
        model.addAttribute("denoviIznajmuvanje", rezervacija.getDenoviIznajmuvanje());
        model.addAttribute("cenaPoDen", rezervacija.getVozilo().getCenaPoDen());
        model.addAttribute("amount",  (int)(rezervacija.getTotal())); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @PostMapping("/charge")
    public String charge( ChargeRequest chargeRequest, Model model)
            throws StripeException {

        chargeRequest.setDescription("Rent-a-car payment");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("amount",charge.getAmount() / 100);
        model.addAttribute("description",charge.getDescription());

        Transactions transaction = new Transactions();
        transaction.setIDD(charge.getId());
        transaction.setStatus(charge.getStatus());
        transaction.setChargeId(charge.getId());
        transaction.setBalanceTransaction(charge.getBalanceTransaction());
        transaction.setAmount(charge.getAmount() / 100);
        transaction.setDescription(charge.getDescription());

        paymentService.saveTransaction(transaction);


        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
