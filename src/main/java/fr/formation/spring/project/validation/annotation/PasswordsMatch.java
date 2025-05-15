// fr.formation.spring.validation.annotation.PasswordsMatch
package fr.formation.spring.project.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.formation.spring.project.validation.validator.PasswordsMatchValidator;

// @Target(ElementType.TYPE) : L'annotation s'applique à une classe
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Documented
public @interface PasswordsMatch {

    String message() default "Les mots de passe ne correspondent pas.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // On peut ajouter des paramètres pour spécifier les noms des champs à comparer
    // String passwordFieldName() default "password";
    // String confirmPasswordFieldName() default "confirmPassword";
    // Mais pour simplifier, on va les coder en dur dans le validateur pour cet exemple.
}