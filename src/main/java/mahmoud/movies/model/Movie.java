package mahmoud.movies.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private String releaseDate;
    @Column(name = "trailer_link")
    private String trailerLink;
    private  String Poster;
    private List<String> genres;
    private List<String> backdrops;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Review> reviews;


    public Movie() {
    }

    public Movie(String imdId, String title, String releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
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
    public void addReview(Review review){
        if(reviews == null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
        review.setMovie(this);
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
                '}';
    }
}
