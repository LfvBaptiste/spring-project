package fr.formation.spring.project.controller;

import fr.formation.spring.project.dto.UserLoginDto;
import fr.formation.spring.project.dto.UserRegistrationDto;
import fr.formation.spring.project.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.formation.spring.project.service.UserService;

@Controller
@RequestMapping("/users")
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) {this.userService = userService;}

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/users/login";
    }

    // Affiche le formulaire vide
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("userDto")) { // Evite d'écraser en cas d'erreur
            model.addAttribute("userDto", new UserRegistrationDto());
        }
        return "users/user-registration";
    }

    // Traite la soumission du formulaire
    @PostMapping("/register")
    public String processRegistration(
            @Valid
            @ModelAttribute("userDto") UserRegistrationDto userDTO, // Spring lie les données du formulaire à cet objet
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return "users/user-registration";
        }
        User userEntity = this.userService.convertDtoToEntity(userDTO);
        this.userService.createUser(userDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("userDto")) { // Evite d'écraser en cas d'erreur
            model.addAttribute("userDto", new UserLoginDto());
        }
        return "users/user-login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid
            @ModelAttribute("userDto") UserLoginDto userDTO,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return "users/user-login";
        }
        Boolean status = this.userService.checkUserLogin(userDTO, userDTO.getUsername(), userDTO.getPasswordHash());
        if(status) {
            return "redirect:/concerts/list";
        } else {
            //message flash pour prevenir que le mot de passe ne correspond pas
            return "redirect:/users/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }


}
