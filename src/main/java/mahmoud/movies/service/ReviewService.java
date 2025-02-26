package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.Movie;
import mahmoud.movies.model.Review;
import mahmoud.movies.repository.MovieRepository;
import mahmoud.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository ;

    public List<Review> getReviewsForMovie(int movieId) {

        return reviewRepository.findByMovieId(movieId);
    }

        public Review createReview(String body, int movieId) {
       // Movie movie = movieService.getSingleMovie(movieId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()->new EntityNotFoundException(("Movie with id " + movieId + " not found")));
        Review review = new Review(body);
        review.setMovie(movie);
        return reviewRepository.save(review);
    }
}
