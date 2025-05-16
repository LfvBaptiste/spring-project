package fr.formation.spring.project.service;

import fr.formation.spring.project.dto.ConcertRegistrationDto;
import fr.formation.spring.project.model.Band;
import fr.formation.spring.project.model.Concert;
import fr.formation.spring.project.model.Venue;
import fr.formation.spring.project.repository.BandRepository;
import fr.formation.spring.project.repository.ConcertRepository;
import fr.formation.spring.project.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final BandRepository bandRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public ConcertService(
            ConcertRepository concertRepository,
            BandRepository bandRepository,
            VenueRepository venueRepository
    ) {
        this.concertRepository = concertRepository;
        this.bandRepository = bandRepository;
        this.venueRepository = venueRepository;
    }

    public Concert convertDtoToEntity(ConcertRegistrationDto dto) {
        Concert concert = new Concert();
        concert.setConcertDate(dto.getConcertDate());
        concert.setDescription(dto.getDescription());
        Optional<Band> concertBand = this.bandRepository.findById(dto.getBand_id());
        concert.setBand(concertBand);
        Optional<Venue> concertVenue = this.venueRepository.findById(dto.getVenue_id());
        concert.setVenue(concertVenue);
        return concert;
    }

    public ConcertRegistrationDto convertEntityToDto(Concert concert) {
        ConcertRegistrationDto dto = new ConcertRegistrationDto();
        dto.setConcertDate(concert.getConcertDate());
        dto.setDescription(concert.getDescription());
        Optional<Band> concertBand = this.bandRepository.findById(concert.getBand().getId());
        concertBand.ifPresent(band -> dto.setBand_id(band.getId()));
        Optional<Venue> concertVenue = this.venueRepository.findById(concert.getVenue().getId());
        concertVenue.ifPresent(venue -> dto.setVenue_id(venue.getId()));
        return dto;
    }

    public List<Concert> getAllConcert(){
        return this.concertRepository.getAllOrderByConcertDate(LocalDate.now());
    }

    public void addConcert(ConcertRegistrationDto concertRegistrationDto) {
        Concert concert = convertDtoToEntity(concertRegistrationDto);
        System.out.println(concert);
        concertRepository.save(concert);

    }
}
