package proektwp.proektwp.web.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.SopstvenikNotFoundException;
import proektwp.proektwp.exceptions.UserNotFoundException;
import proektwp.proektwp.models.Sopstvenik;
import proektwp.proektwp.models.User;
import proektwp.proektwp.service.SopstvenikService;
import proektwp.proektwp.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserControllerThymeleaf {

    private UserService userService;

    public UserControllerThymeleaf (UserService userService){
        this.userService = userService;
    }


    @GetMapping("/thymeleaf/user")
    public String Users(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("user", new User());

        return "users";
    }



    @GetMapping("/thymeleaf/user/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUserShow(Model model){
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("user", new User());
        return "user.add";
    }


    @PostMapping("/thymeleaf/user/add/")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(HttpServletRequest request, Model model) {
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String adresa = request.getParameter("adresa");
        int godini  = Integer.parseInt(request.getParameter("godini"));

        User newUser = new User();
        newUser.setIme(ime);
        newUser.setPrezime(prezime);
        newUser.setAdresa(adresa);
        newUser.setGodini(godini);
        userService.save(newUser);

        model.addAttribute("userList",userService.findAll());
        return "redirect:/thymeleaf/user";

    }


    @DeleteMapping("/thymeleaf/delete/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("userId"));
        userService.deleteById(userId);
        return "redirect:/thymeleaf/user";
    }

    @ExceptionHandler({UserNotFoundException.class})
    @GetMapping("/thymeleaf/user/{id}")
    public String Show(@PathVariable("id") String id, Model model){
        Long ID = Long.parseLong(id);

        User user = userService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());

        model.addAttribute("user",user);

        return "user.details";
    }


}
