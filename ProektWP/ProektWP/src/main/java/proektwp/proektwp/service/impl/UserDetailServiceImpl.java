package proektwp.proektwp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proektwp.proektwp.models.UserSecurity;
import proektwp.proektwp.repository.UserSecurityRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserSecurityRepository repository;

    @Autowired
    public UserDetailServiceImpl(UserSecurityRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserSecurity curruser = repository.findByUsername(username);

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), true,
                true, true, true, AuthorityUtils.createAuthorityList(curruser.getRole()));

        System.out.println("ROLE: " + curruser.getRole());
        return user;
    }
}
