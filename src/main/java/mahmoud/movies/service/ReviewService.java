package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.Movie;
import mahmoud.movies.model.Review;
import mahmoud.movies.repository.MovieRepository;
import mahmoud.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieService movieService;

    public Review createReview(String body, int movieId) {
        Movie movie = movieService.getSingleMovie(movieId);
        Review review = new Review(body);
        review.setMovie(movie);
        return reviewRepository.save(review);


    }
}
