package proektwp.proektwp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import proektwp.proektwp.service.impl.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@ComponentScan("proektwp.proektwp.config")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests().antMatchers("/login", "/thymeleaf/vozilo","/signup", "/css/**","/assets/vendor/**","/saveuser","/kategorija","/kategorija/**","/vozilo","/vozilo/**","/proizvoditel","/proizvoditel/**","/komentar","/komentar/**","/rezervacija","/rezervacija/**","/sopstvenik","/sopstvenik/**","/vozilo","/vozilo/**","/user","/user/**").permitAll() // Enable css when logged out


                .and()

                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/thymeleaf/vozilo")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login").and().csrf().disable();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
