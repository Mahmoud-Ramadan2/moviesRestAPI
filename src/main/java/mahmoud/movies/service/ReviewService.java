package mahmoud.movies.service;

import mahmoud.movies.exception.EntityNotFoundException;
import mahmoud.movies.model.Movie;
import mahmoud.movies.model.Review;
import mahmoud.movies.repository.MovieRepository;
import mahmoud.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    public Page<Review> getReviewsForMovie(int movieId, int page, int size) {
        // no need to sort here
//        List<Review> reviews = reviewRepository.findByMovieId(movieId);
//               reviews.sort(Comparator.comparing(Review::getCreatedAt).reversed());
        PageRequest pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());

        return reviewRepository.findByMovieId(movieId, pageable);

    }

    public Review createReview(String body, int movieId) {
        // Movie movie = movieService.getSingleMovie(movieId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(("Movie with id " + movieId + " not found")));
        Review review = new Review(body);
        review.setMovie(movie);
        return reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Movie with id " + id + " not found")));
        reviewRepository.delete(review);
    }
}
