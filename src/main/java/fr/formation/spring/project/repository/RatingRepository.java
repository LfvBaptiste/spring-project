package fr.formation.spring.project.repository;

import fr.formation.spring.project.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
