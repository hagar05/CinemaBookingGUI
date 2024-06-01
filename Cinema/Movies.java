package Cinema;

public class Movies {

    protected String name;
    protected String genre;
    protected int minAge;
    protected int movieId;
    

    public Movies(String name, String genre, int minAge, int movieId) {
        this.name = name;
        this.genre = genre;
        this.minAge = minAge;
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMovieId() {
        return movieId;
    }

}
