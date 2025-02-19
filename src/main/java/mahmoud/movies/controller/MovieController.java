package mahmoud.movies.controller;


import jakarta.websocket.server.PathParam;
import mahmoud.movies.model.Movie;
import mahmoud.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable int id){
        return ResponseEntity.ok(movieService.getSingleMovie(id)); // Automatically handled by @RestControllerAdvice

    }
    @GetMapping("/imdb/{imdId}")
public ResponseEntity<Movie> fineMovieByImdId(@PathVariable String imdId){
        return ResponseEntity.ok(movieService.fineMovieByImdId(imdId));
    }
}
