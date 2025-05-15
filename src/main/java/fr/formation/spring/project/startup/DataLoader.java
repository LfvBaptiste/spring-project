package fr.formation.spring.project.startup;

import fr.formation.spring.project.dto.UserLoginDto;
import fr.formation.spring.project.dto.UserRegistrationDto;
import fr.formation.spring.project.service.UserService;
import jakarta.annotation.PostConstruct;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public DataLoader(
        UserService userService
    ) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("admin");
        userDto.setEmail("admin@admin.com");
        userDto.setPasswordHash("1234");
        this.userService.createUser(userDto);
        System.out.println("Le mot de passe est obligatoire.");
    }
}
