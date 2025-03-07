package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.User;
import mahmoud.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User FindUserByEmail (String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("user with"+ email +"not found"));

    }
    public User addUser (User user){
        return userRepository.save(user);
    }
}
