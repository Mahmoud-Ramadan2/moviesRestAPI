package mahmoud.movies.converter;

import mahmoud.movies.DTO.ReviewRequest;
import mahmoud.movies.model.Review;
import mahmoud.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestParam ReviewRequest reviewRequest){

        Review createdReview = reviewService.createReview(reviewRequest.getBody(), reviewRequest.getMovieId());
        // Build response with 201 Created and Location header
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId()) // Assuming Review has an ID
                .toUri();

        return ResponseEntity.created(location).body(createdReview);

    }

}
