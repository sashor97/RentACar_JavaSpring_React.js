package proektwp.proektwp.web;

import org.springframework.web.bind.annotation.*;
import proektwp.proektwp.exceptions.KomentarNotFoundException;
import proektwp.proektwp.exceptions.ManufacturerNotFoundException;
import proektwp.proektwp.exceptions.UserNotFoundException;
import proektwp.proektwp.models.Komentar;
import proektwp.proektwp.models.Proizvoditel;
import proektwp.proektwp.models.User;
import proektwp.proektwp.service.ProizvoditelService;
import proektwp.proektwp.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*","localhost:3000"})
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers (){
        return userService.findAll();
    }

    @PostMapping("/user/add")
    public User addUser (@RequestBody User user){
        return userService.save(user);
    }

    @ExceptionHandler({UserNotFoundException.class})
    @GetMapping("/user/{id}")
    public Optional<User> prikazi(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        User user = userService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());

        return userService.findById(ID);

    }
    @GetMapping("user/delete/{id}")
    public void delete(@PathVariable("id") String id){
        Long ID = Long.parseLong(id);
        User u = userService.findAll().stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());

        userService.deleteById(ID);

    }

}
