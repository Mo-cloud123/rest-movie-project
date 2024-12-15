package se.mo.restmovieproject.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.mo.restmovieproject.model.Movie;
import se.mo.restmovieproject.movieDatabase.MovieRepository;

import java.util.List;

/**
 * Denna klass hanterar API:et för filmer.
 * Här kan man skapa, läsa, uppdatera och ta bort filmer via HTTP-förfrågningar.
 * @author Mohamad
 * @version 1.0
 */
@Path("/movies") // URL:en för att nå API:et är "/movies".
public class MovieResource {
    @Inject
    private MovieRepository movieRepository; // Kopplar till databaslagret.

    /**
     * Hämtar alla filmer från databasen.
     * @return en lista med alla filmer i JSON-format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Svarar med data i JSON-format.
    public List<Movie> getMovies() {
        return movieRepository.findMovies(); // Hämtar alla filmer från databasen.
    }

    /**
     * Hämtar en specifik film från databasen baserat på ID.
     * @param id ID:t för filmen som ska hämtas.
     * @return en Response med filmen (200 OK) eller ett felmeddelande (404 Not Found).
     */
    @GET
    @Path("/{id}") // "{id}" gör att API:et kan ta emot ett ID från URL:en.
    @Produces(MediaType.APPLICATION_JSON) // Returnerar resultatet som JSON.
    public Response getMovie(@PathParam("id") Long id) {
        Movie movie = movieRepository.findMovie(id); // Letar efter filmen i databasen.
        if (movie == null) { // Om filmen inte hittas.
            return Response.status(Response.Status.NOT_FOUND).build(); // Returnerar 404 Not Found.
        }
        return Response.ok(movie).build(); // Returnerar filmen med status 200 OK.
    }

    /**
     * Lägger till en ny film i databasen.
     * @param movie filmen som ska skapas, skickad som JSON.
     * @return en Response som indikerar att filmen skapades (201 Created).
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON) // Förväntar sig JSON-data i förfrågan.
    public Response addMovie(Movie movie) {
        movieRepository.createMovie(movie); // Lägger till filmen i databasen.
        return Response.status(Response.Status.CREATED).entity("Movie created").build();
        // Returnerar 201 Created med ett meddelande.
    }

    /**
     * Uppdaterar en befintlig film baserat på ID.
     * @param id ID:t för filmen som ska uppdateras.
     * @param updatedMovie filmen med de nya värdena, skickad som JSON.
     * @return en Response som indikerar om uppdateringen lyckades (200 OK) eller inte (404 Not Found).
     */
    @PUT
    @Path("/{id}") // ID:t för filmen som ska uppdateras tas från URL:en.
    @Consumes(MediaType.APPLICATION_JSON) // Förväntar sig JSON-data.
    public Response updateMovie(@PathParam("id") Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findMovie(id); // Letar efter filmen i databasen.
        if (existingMovie == null) { // Om filmen inte finns.
            return Response.status(Response.Status.NOT_FOUND).entity("Movie not found").build();
            // Returnerar 404 Not Found.
        }
        // Uppdaterar filmens egenskaper med nya värden.
        existingMovie.setTitle(updatedMovie.getTitle()); // Uppdaterar titel.
        existingMovie.setGenre(updatedMovie.getGenre()); // Uppdaterar genre.
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear()); // Uppdaterar år.
        existingMovie.setDescription(updatedMovie.getDescription()); // Uppdaterar beskrivning.
        existingMovie.setDirector(updatedMovie.getDirector()); // Uppdaterar regissör.

        movieRepository.updateMovie(existingMovie); // Sparar ändringarna i databasen.
        return Response.ok("Movie updated successfully").build();
        // Returnerar 200 OK med ett meddelande.
    }

    /**
     * Tar bort en film från databasen baserat på ID.
     * @param id ID:t för filmen som ska tas bort.
     * @return en Response som indikerar om borttagningen lyckades (200 OK) eller inte (404 Not Found).
     */
    @DELETE
    @Path("/{id}") // ID:t för filmen som ska tas bort tas från URL:en.
    public Response deleteMovie(@PathParam("id") Long id) {
        Movie existingMovie = movieRepository.findMovie(id); // Letar efter filmen i databasen.
        if (existingMovie == null) { // Om filmen inte finns.
            return Response.status(Response.Status.NOT_FOUND).entity("Movie not found").build();
            // Returnerar 404 Not Found.
        }
        movieRepository.deleteMovie(id); // Tar bort filmen från databasen.
        return Response.ok("Movie deleted successfully").build();
        // Returnerar 200 OK med ett meddelande.
    }
}
