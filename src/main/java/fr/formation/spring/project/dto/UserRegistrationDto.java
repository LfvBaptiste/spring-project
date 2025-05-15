package fr.formation.spring.project.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import fr.formation.spring.project.validation.annotation.PasswordsMatch; // Importer

@Getter
@Setter
@PasswordsMatch(message = "La confirmation doit être identique au mot de passe.")
public class UserRegistrationDto {

    // Getters et Setters obligatoires pour le data binding
    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit contenir entre 3 et 20 caractères.")
    private String username;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "Le format de l'email est invalide.")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 8 caractères.")
    // On pourrait ajouter @Pattern pour la complexité (ex : majuscule, chiffre...)
    private String passwordHash;

    @NotBlank(message = "La confirmation du mot de passe est obligatoire.")
    private String confirmPassword; // Champ spécifique au formulaire

}