package mahmoud.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import mahmoud.movies.converter.StringListConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imd_id")
    private String imdId;
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "trailer_link")
    private String trailerLink;
    private String Poster;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT") // Store as JSON string
    private List<String> genres;
    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT") // Store as JSON string
    private List<String> backdrops;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    @JsonManagedReference
    private List<Review> reviews;


    public Movie() {
    }

    public Movie(String imdId, String title, LocalDate releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops) {
        this.imdId = imdId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        Poster = poster;
        this.genres = genres;
        this.backdrops = backdrops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdId() {
        return imdId;
    }

    public void setImdId(String imdId) {
        this.imdId = imdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<String> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // Convenience method
    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
        review.setMovie(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(imdId, movie.imdId) && Objects.equals(title, movie.title) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(trailerLink, movie.trailerLink) && Objects.equals(Poster, movie.Poster) && Objects.equals(genres, movie.genres) && Objects.equals(backdrops, movie.backdrops) && Objects.equals(reviews, movie.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdId, title, releaseDate, trailerLink, Poster, genres, backdrops, reviews);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", imdId='" + imdId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", trailerLink='" + trailerLink + '\'' +
                ", Poster='" + Poster + '\'' +
                ", genres=" + genres +
                ", backdrops=" + backdrops +
                ", reviews=" + reviews +
                '}';
    }
}
