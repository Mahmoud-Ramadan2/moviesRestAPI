package mahmoud.movies.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import mahmoud.movies.model.Movie;
import mahmoud.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;


@Tag(name = "Movie APi.", description = "Operations related to Movies.")
@RestController
@RequestMapping("api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    @Operation(summary = "Get a Movie by ID", description = "Fetch Movie details by Movie ID")
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable int id) {
        return ResponseEntity.ok(movieService.getSingleMovie(id)); // Automatically handled by @RestControllerAdvice

    }

    @GetMapping("/imdb/{imdId}")
    public ResponseEntity<Movie> fineMovieByImdId(@PathVariable String imdId) {
        return ResponseEntity.ok(movieService.fineMovieByImdId(imdId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) LocalDate releaseDate){
        return  ResponseEntity.ok(movieService.searchMovies(title,releaseDate));

    }



    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {

        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id,@RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.udateMovie(id, movie));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id){
        movieService.deleteReview(id);

        // return ResponseEntity.ok("Review deleted successfully");
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

    }

}
