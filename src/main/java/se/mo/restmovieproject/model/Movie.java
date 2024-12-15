package se.mo.restmovieproject.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;

/**
 * Denna klass representerar en film i systemet.
 * Den kopplas till databasen som en tabell och används för att skicka och ta emot data via API:et.
 * @author Mohamad
 * @version 1.0
 */

// När data skickas som JSON ska fälten följa denna ordning.
@JsonbPropertyOrder({"title", "genre", "releaseYear", "director", "description"})
// Markerar att denna klass ska kopplas till en tabell i databasen.
@Entity
// Tabellnamnet i databasen är "movies".
@Table(name = "movies")
public class Movie {

    /**
     * Denna variabel representerar filmens unika ID.
     * Den används för att identifiera en film i databasen.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Databasen genererar unikt ID automatiskt.
    @Column(name = "id", nullable = false) // Koppling till kolumnen "id" som inte får vara tom.
    private Long id;

    /**
     * Titel för filmen.
     */
    private String title;

    /**
     * Genre för filmen, t.ex. "Drama" eller "Action".
     */
    private String genre;

    /**
     * Året då filmen släpptes.
     */
    private int releaseYear;

    /**
     * En kort beskrivning av filmen.
     */
    private String description;

    /**
     * Regissören för filmen.
     */
    private String director;

    /**
     * En tom konstruktor krävs för att JPA ska kunna skapa instanser av klassen.
     */
    public Movie() {
    }

    /**
     * Konstruktor som låter dig skapa ett Movie-objekt med alla fält på en gång.
     * @param title titel för filmen.
     * @param genre genre för filmen.
     * @param releaseYear året då filmen släpptes.
     * @param description en kort beskrivning av filmen.
     * @param director regissören för filmen.
     */
    public Movie(String title, String genre, int releaseYear, String description, String director) {
        this.title = title;           // Sätter titel för filmen.
        this.genre = genre;           // Sätter genre för filmen.
        this.releaseYear = releaseYear; // Sätter året då filmen släpptes.
        this.description = description; // Sätter en beskrivning av filmen.
        this.director = director;     // Sätter regissören för filmen.
    }

    /**
     * Hämtar filmens ID.
     * @return ID:t för filmen.
     */
    public Long getId() {
        return id;
    }

    /**
     * Ändrar filmens ID.
     * @param id det nya ID:t för filmen.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Hämtar filmens titel.
     * @return titel för filmen.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ändrar filmens titel.
     * @param title den nya titeln för filmen.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Hämtar filmens genre.
     * @return genre för filmen.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Ändrar filmens genre.
     * @param genre den nya genren för filmen.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Hämtar året då filmen släpptes.
     * @return året då filmen släpptes.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Ändrar året då filmen släpptes.
     * @param releaseYear det nya året då filmen släpptes.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Hämtar filmens beskrivning.
     * @return beskrivning av filmen.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ändrar filmens beskrivning.
     * @param description den nya beskrivningen av filmen.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Hämtar regissörens namn.
     * @return regissören för filmen.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Ändrar regissörens namn.
     * @param director det nya namnet på regissören.
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
