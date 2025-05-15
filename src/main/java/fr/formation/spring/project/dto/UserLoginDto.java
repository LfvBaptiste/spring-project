package fr.formation.spring.project.dto;

import fr.formation.spring.project.validation.annotation.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit contenir entre 3 et 20 caractères.")
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 3 caractères.")
    // On pourrait ajouter @Pattern pour la complexité (ex : majuscule, chiffre...)
    private String passwordHash;
}
