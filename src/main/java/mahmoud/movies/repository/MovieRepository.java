package mahmoud.movies.repository;

import mahmoud.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Optional<Movie> findByImdId(String imdId);

    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByReleaseDate(LocalDate releaseDate);

}
