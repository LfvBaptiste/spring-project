package fr.formation.spring.project.startup;

import fr.formation.spring.project.dto.UserLoginDto;
import fr.formation.spring.project.dto.UserRegistrationDto;
import fr.formation.spring.project.model.Band;
import fr.formation.spring.project.model.Venue;
import fr.formation.spring.project.service.BandService;
import fr.formation.spring.project.service.UserService;
import fr.formation.spring.project.service.VenueService;
import jakarta.annotation.PostConstruct;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final BandService bandService;
    private final VenueService venueService;

    @Autowired
    public DataLoader(
        UserService userService,
        BandService bandService,
        VenueService venueService
    ) {
        this.userService = userService;
        this.bandService = bandService;
        this.venueService = venueService;
    }

    @Override
    public void run(String... args) throws Exception {
        UserRegistrationDto userDto = new UserRegistrationDto();
        userDto.setUsername("admin");
        userDto.setEmail("admin@admin.com");
        userDto.setPasswordHash("1234");
        this.userService.createUser(userDto);

        userDto = new UserRegistrationDto();
        userDto.setUsername("Jean");
        userDto.setEmail("jean@jean.com");
        userDto.setPasswordHash("1234");
        this.userService.createUser(userDto);

        Band band = new Band();
        band.setName("Gojira");
        band.setGenre("Metal");
        this.bandService.createBand(band);

        Band band1 = new Band();
        band1.setName("PSO THUG");
        band1.setGenre("Rap");
        this.bandService.createBand(band1);

        Venue venue = new Venue();
        venue.setName("Olympia");
        venue.setCity("Paris");
        this.venueService.createVenue(venue);

        Venue venue1 = new Venue();
        venue1.setName("Accor Arena");
        venue1.setCity("Paris");
        this.venueService.createVenue(venue1);
    }
}
