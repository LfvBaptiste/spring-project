package fr.formation.spring.project.service;

import fr.formation.spring.project.model.Band;
import fr.formation.spring.project.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {

    private final BandRepository bandRepository;

    @Autowired
    public BandService(BandRepository bandRepository) { this.bandRepository = bandRepository; }

    public void createBand(Band band){
        this.bandRepository.save(band);
    }

    public List<Band> getBands(){
        return bandRepository.findAll();
    }
}
