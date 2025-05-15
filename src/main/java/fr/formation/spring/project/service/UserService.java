package fr.formation.spring.project.service;

import fr.formation.spring.project.dto.UserLoginDto;
import fr.formation.spring.project.dto.UserRegistrationDto;
import fr.formation.spring.project.model.User;
import fr.formation.spring.project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository; // Injection du repository via le constructeur (bonne pratique)
    private final HttpSession session;

    @Autowired // L'injection par constructeur est préférée à @Autowired sur le champ
    public UserService(
            UserRepository userRepository,
            HttpSession session
    ) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public User convertDtoToEntity(UserRegistrationDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        return user;
    }

    public UserRegistrationDto convertEntityToDto(User user) {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public void createUser(UserRegistrationDto dto) {
        User newUser = convertDtoToEntity(dto);
        newUser.setPasswordHash(BCrypt.hashpw(dto.getPasswordHash(), BCrypt.gensalt()));
        newUser.setEmail(dto.getEmail());
        newUser.setUsername(dto.getUsername());

        userRepository.save(newUser);
    }

    public Boolean checkUserLogin(
            UserLoginDto dto,
            String username,
            String password
    ) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if(user.isPresent()) {
            boolean status = BCrypt.checkpw(password, user.get().getPasswordHash());
            if(!status) {
                System.out.println("Le mot de passe est incorrect.");
                return false;
            } else {
                session.setAttribute("userId", user.get().getId());
                return true;
            }
        } else {
            return false;
        }
    }
}
