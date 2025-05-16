package fr.formation.spring.project.dto;

import fr.formation.spring.project.model.Band;
import fr.formation.spring.project.model.Venue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConcertRegistrationDto {

    @NotNull(message = "La date du concert est obligatoire.")
    private LocalDate concertDate;

    @Size(min = 3, message = "La description doit contenir au moins 3 caractères.")
    private String description;

    @NotNull(message = "Le lieu du concert est obligatoire.")
    private Long venue_id;

    @NotNull(message = "Le groupe du concert est obligatoire.")
    private Long band_id;
}
