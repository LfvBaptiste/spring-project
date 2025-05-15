package fr.formation.spring.project.model;

import jakarta.persistence.*; // Utilise jakarta.persistence.* avec Spring Boot 3+
// import javax.persistence.*; // Utilise javax.persistence.* avec Spring Boot 2.x
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate; // Utiliser java.time pour les dates modernes
import java.util.ArrayList;
import java.util.List;

@Entity // Marque cette classe comme une entité JPA
@Table(name = "users") // Mappe à la table "users"
@Getter // Lombok: génère les getters
@Setter // Lombok: génère les setters
@NoArgsConstructor // Lombok: génère un constructeur sans arguments (requis par JPA)
@AllArgsConstructor // Lombok: génère un constructeur avec tous les arguments
public class User {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Stratégie d'auto-incrémentation (commune)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50) // Colonne spécifique
    private String username; // Nom d'utilisateur

    @Column(nullable = false, unique = true, length = 100) // La colonne s'appellera "email" par défaut
    private String email; // Adresse email

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<Rating>();
}