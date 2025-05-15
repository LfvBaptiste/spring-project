// fr.formation.spring.validation.validator.PasswordsMatchValidator
package fr.formation.spring.project.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import fr.formation.spring.project.dto.UserRegistrationDto; // Type spécifique ici
import fr.formation.spring.project.validation.annotation.PasswordsMatch;

// Valide un objet de type UserRegistrationDto
public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, UserRegistrationDto> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        // Initialisation si nécessaire
    }

    @Override
    public boolean isValid(UserRegistrationDto userDto,
                           ConstraintValidatorContext context) {
        String password = userDto.getPasswordHash();
        String confirmPassword = userDto.getConfirmPassword();

        // Si les deux sont null ou vides, on considère que c'est ok
        // (la validation @NotBlank sur les champs gérera l'obligation)
        // Ou on pourrait décider que c'est invalide ici aussi.
        if (password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {
            return true; // Laisser @NotBlank faire son travail sur les champs
        }

        boolean passwordsMatch = password.equals(confirmPassword);

        if (!passwordsMatch) {
            // Si on veut que l'erreur soit associée à un champ spécifique (ex: confirmPassword)
            // Désactive le message global par défaut
            // context.disableDefaultConstraintViolation();
            // context.buildConstraintViolationWithTemplate(
            //      context.getDefaultConstraintMessageTemplate()
            // )
            // Associe à ce champ
            //        .addPropertyNode("confirmPassword")
            //        .addConstraintViolation();

            // Par défaut (sans le code ci-dessus),
            // l'erreur sera globale (class-level)
        }

        return passwordsMatch;
    }
}