package common;

public class Request {
    private String type;
    private Rating rating;

    public Request(String type, Rating rating) {
        this.type = type;
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public Rating getRating() {
        return rating;
    }
}
