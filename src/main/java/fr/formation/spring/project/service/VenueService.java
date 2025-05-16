package fr.formation.spring.project.service;

import fr.formation.spring.project.model.Venue;
import fr.formation.spring.project.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository) { this.venueRepository = venueRepository; }

    public void createVenue(Venue venue){
        this.venueRepository.save(venue);
    }

    public List<Venue> getVenues(){
        return venueRepository.findAll();
    }
}
