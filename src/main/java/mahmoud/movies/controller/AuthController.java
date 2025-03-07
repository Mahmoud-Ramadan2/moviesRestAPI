package mahmoud.movies.controller;


import mahmoud.movies.DTO.LoginCredentials;
import mahmoud.movies.model.User;
import mahmoud.movies.security.JWTUtil;
import mahmoud.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

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
public ResponseEntity<String> getTest(){
    return ResponseEntity.ok("HHHHHHHHH");
}
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@RequestBody User user){
    String encodedPass = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPass);
    userService.addUser(user);
    String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginHandler(@RequestBody LoginCredentials body) {
        try {

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
            authManager.authenticate(authToken);

            String token = jwtUtil.generateToken(body.getEmail());
            return ResponseEntity.ok(Collections.singletonMap("jwt-token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(Collections.singletonMap("error", "Invalid Login Credentials"));
        }

    }

}