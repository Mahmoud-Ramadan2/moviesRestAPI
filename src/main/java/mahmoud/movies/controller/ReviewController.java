package mahmoud.movies.controller;

import mahmoud.movies.DTO.ReviewRequest;
import mahmoud.movies.model.Review;
import mahmoud.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{movieId}")
    public ResponseEntity<Page<Review>> getReviewsForMovie(
            @PathVariable int movieId,
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "1") int size)
    {

        return ResponseEntity.ok(reviewService.getReviewsForMovie(movieId, page, size));
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id){
        reviewService.deleteReview(id);

       // return ResponseEntity.ok("Review deleted successfully");
       return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

    }


}


