package mahmoud.movies.security;

import mahmoud.movies.model.Role;
import mahmoud.movies.model.User;
import mahmoud.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.FindUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                email, user.getPassword(),
                user.getRoles().stream().map(r-> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList())
                );

    }


}
