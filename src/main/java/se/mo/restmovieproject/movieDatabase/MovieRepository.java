package se.mo.restmovieproject.movieDatabase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import se.mo.restmovieproject.model.Movie;

import java.util.List;

/**
 * Denna klass hanterar all kommunikation med databasen
 * för att skapa, läsa, uppdatera och ta bort filmer.
 * @author Mohamad
 * @version 1.0
 */
@ApplicationScoped // Gör klassen tillgänglig i hela projektet.
@Transactional // Hanterar databastransaktioner, ändringar sparas eller ångras om fel uppstår.
public class MovieRepository {

    /**
     * EntityManager används för att kommunicera med databasen.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Hämtar alla filmer från databasen.
     * @return en lista med alla filmer.
     */
    public List<Movie> findMovies() {
        // SQL-fråga för att hämta alla filmer från tabellen "movies".
        String sql = "select * from movies";
        // Skapar en fråga med SQL-koden.
        Query query = entityManager.createNativeQuery(sql);
        // Hämtar resultatet som en lista av filmer.
        List<Movie> movies = query.getResultList();
        return movies; // Returnerar listan med filmer.
    }

    /**
     * Skapar en ny film och sparar den i databasen.
     * @param movie den film som ska sparas.
     */
    public void createMovie(Movie movie) {
        entityManager.persist(movie); // Lägger till filmen i databasen.
    }

    /**
     * Uppdaterar en film som redan finns i databasen.
     * @param movie den uppdaterade filmens data.
     */
    public void updateMovie(Movie movie) {
        entityManager.merge(movie); // Uppdaterar filmen i databasen.
    }

    /**
     * Hämtar en specifik film från databasen med hjälp av dess ID.
     * @param id ID:t för filmen som ska hämtas.
     * @return filmen med det givna ID:t, eller null om den inte finns.
     */
    public Movie findMovie(Long id) {
        return entityManager.find(Movie.class, id); // Letar upp och returnerar filmen.
    }

    /**
     * Tar bort en film från databasen baserat på dess ID.
     * @param id ID:t för filmen som ska tas bort.
     */
    public void deleteMovie(Long id) {
        // Hämta filmen som ska tas bort.
        Movie movie = findMovie(id);
        // Kontrollera att filmen finns.
        if (movie != null) {
            entityManager.remove(movie); // Tar bort filmen från databasen.
        }
    }
}
