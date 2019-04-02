package common;

public class Request {
    private String type;
    private Review review;

    public Request(String type, Review review) {
        this.type = type;
        this.review = review;
    }

    public String getType() {
        return type;
    }

    public Review getReview() {
        return review;
    }
}
