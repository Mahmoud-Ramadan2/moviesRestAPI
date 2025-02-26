package mahmoud.movies.repository;

import mahmoud.movies.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByMovieId(int movieId);

}
