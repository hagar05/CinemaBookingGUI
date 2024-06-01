package Cinema;

public class MovieScreening {

    private Movies name;
    private Movies movieId;
    public int availableSeats = 200;
    private int reservedSeats;
    private String screen;
    private String timeSlot;

    public MovieScreening(Movies name, Movies movieId, int availableSeats, String screen, String timeSlot) {
        this.name = name;
        this.movieId = movieId;
        this.availableSeats = availableSeats;
        this.screen = screen;
        this.timeSlot = timeSlot;

    }

    public boolean reserveSeat(int numOfSeats) {
        if (availableSeats > numOfSeats) {
            availableSeats = availableSeats - numOfSeats;
            reservedSeats = reservedSeats + numOfSeats;
            return true;
        } else {
            return false;
        }
    }

    public Movies getMovie() {
        return name;
    }

}
