package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.Role;
import mahmoud.movies.model.User;
import mahmoud.movies.repository.RoleRepository;
import mahmoud.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public User FindUserByEmail (String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("user with"+ email +"not found"));

    }
    public User addUser (User user){

        Role role = roleRepository.findByRole("ROLE_USER").orElseGet(
                ()->{
                    Role newRole = new Role("ROLE_USER");
                    return roleRepository.save(newRole);

                }
        );
        user.addRole(role);
        return userRepository.save(user);
    }
}
