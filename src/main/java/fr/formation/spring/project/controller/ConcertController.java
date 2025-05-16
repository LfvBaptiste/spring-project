package fr.formation.spring.project.controller;

import fr.formation.spring.project.dto.ConcertRegistrationDto;
import fr.formation.spring.project.model.Concert;
import fr.formation.spring.project.service.BandService;
import fr.formation.spring.project.service.ConcertService;
import fr.formation.spring.project.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    private final ConcertService concertService;
    private final VenueService venueService;
    private final BandService bandService;

    public ConcertController(
            ConcertService concertService,
            VenueService venueService,
            BandService bandService
    ) {
        this.concertService = concertService;
        this.venueService = venueService;
        this.bandService = bandService;
    }

    @GetMapping("/")
    public String redirectToConcert() {
        return "redirect:/concerts/list";
    }

    @GetMapping("/list")
    public String showAllConcert(Model model) {
        if(!model.containsAttribute("concerts")) {
            model.addAttribute("concerts", concertService.getAllConcert());
        }
        return "concert/concert-list";
    }

    @GetMapping("/add")
    public String addConcert(Model model) {
        model.addAttribute("concertDto", new ConcertRegistrationDto());
        model.addAttribute("venues",this.venueService.getVenues());
        model.addAttribute("bands",this.bandService.getBands());
        return "concert/concert-add";
    }

    @PostMapping("/add")
    public String addConcert(
            @Valid
            @ModelAttribute("concertDto") ConcertRegistrationDto concertDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            System.out.println("error");
            return "concert/concert-add";
        }
        this.concertService.addConcert(concertDto);
        System.out.println("concert added");
        return "redirect:/concerts/list";
    }
}
