package mahmoud.movies.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movie")
public class MovieController {

    @GetMapping
    public String allMovies(){
        return "hi";
    }
}
