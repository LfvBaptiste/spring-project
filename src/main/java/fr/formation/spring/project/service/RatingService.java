package fr.formation.spring.project.service;

import fr.formation.spring.project.model.Rating;
import fr.formation.spring.project.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) { this.ratingRepository = ratingRepository; }

    public void addRating() {

    }
}
