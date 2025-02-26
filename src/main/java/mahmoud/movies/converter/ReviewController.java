package mahmoud.movies.converter;

import mahmoud.movies.DTO.ReviewRequest;
import mahmoud.movies.model.Review;
import mahmoud.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> getReviewsForMovie(@PathVariable int movieId) {

        return ResponseEntity.ok(reviewService.getReviewsForMovie(movieId));
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest){
        Review createdReview = reviewService.createReview(reviewRequest.getBody(), reviewRequest.getMovieId());
        // Build response with 201 Created and Location header
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId()) // Assuming Review has an ID
                .toUri();

        return ResponseEntity.created(location).body(createdReview);

    }


}


