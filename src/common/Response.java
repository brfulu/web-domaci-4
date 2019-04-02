package common;

import java.util.List;

public class Response {
    private String message;
    private List<Rating> ratings;

    public Response(String message, List<Rating> ratings) {
        this.message = message;
        this.ratings = ratings;
    }

    public String getMessage() {
        return message;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
