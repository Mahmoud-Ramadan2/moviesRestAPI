package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.Movie;
import mahmoud.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;



    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getSingleMovie(int id){

        return movieRepository.findById(id)
.orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " not found"));
       }

       public Movie fineMovieByImdId(String imdId){
        return  movieRepository.findByImdId(imdId)

                .orElseThrow(()-> new EntityNotFoundException("Movie with imdId " + imdId + " not found"));

       }
    public List<Movie> searchMovies(String title, LocalDate releaseDate) {
        if (title != null && !title.isEmpty()) {
            return movieRepository.findByTitleContainingIgnoreCase(title);
        } else if (releaseDate != null) {
                return movieRepository.findByReleaseDate(releaseDate);
        }
            else{
              return   movieRepository.findAll();
            }

        }


        public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie udateMovie(int id, Movie updateedMovie) {
        //Movie movie = movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " not found"));
        Optional<Movie> result =  movieRepository.findById(id);
        if(result.isPresent()){
        return movieRepository.save(updateedMovie);
        }else{
            throw new EntityNotFoundException("Movie with id " + id + " not found");
        }

    }

    public void deleteReview(int id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Movie with id " + id + " not found")));
        movieRepository.delete(movie);
    }
}