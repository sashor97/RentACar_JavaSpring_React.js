package proektwp.proektwp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import proektwp.proektwp.models.UserSecurity;
import proektwp.proektwp.repository.UserSecurityRepository;

@SpringBootApplication
public class ProektwpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProektwpApplication.class, args);
    }



}
