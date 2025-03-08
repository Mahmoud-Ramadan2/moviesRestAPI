package mahmoud.movies.controller;


import mahmoud.movies.DTO.LoginCredentials;
import mahmoud.movies.model.Role;
import mahmoud.movies.model.User;
import mahmoud.movies.security.JWTUtil;
import mahmoud.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.Proxy;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("HHHHHHHHH");
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@RequestBody User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        userService.addUser(user);
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        String token = jwtUtil.generateToken(user.getEmail(), authorities);
        return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginHandler(@RequestBody LoginCredentials body) {
        try {

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            //authManager.authenticate(authToken);
            Authentication authentication = authManager.authenticate(authToken);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


            String token = jwtUtil.generateToken(body.getEmail(), authorities);
            return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(Collections.singletonMap("error", "Invalid Login Credentials"));
        }

    }

}