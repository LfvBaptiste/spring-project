package fr.formation.spring.project.repository;

import fr.formation.spring.project.model.Concert;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("SELECT c FROM Concert c ORDER BY c.concertDate ASC")
    List<Concert> getAllOrderByConcertDate(LocalDate concertDate);
}
