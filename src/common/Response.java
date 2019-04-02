package common;

import java.util.List;

public class Response {
    private String message;
    private List<Review> reviews;

    public Response(String message, List<Review> reviews) {
        this.message = message;
        this.reviews = reviews;
    }

    public String getMessage() {
        return message;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
