package mahmoud.movies.DTO;

public class ReviewRequest {
    private String body;
    private int movieId;

    public ReviewRequest() {
    }

    public ReviewRequest(int movieId, String body) {
        this.movieId = movieId;
        this.body = body;
    }

    // Getters and setters
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId;


    }
}

