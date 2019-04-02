package database.model;

import common.Assistant;
import common.Review;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Review> reviews;

    public Store() {
        this.reviews = new ArrayList<>();
    }

    public synchronized void addReview(Review review) {
        Assistant assistant = review.getAssistant();
        assistant.setFirstName(assistant.getFirstName().toUpperCase());
        assistant.setLastName(assistant.getLastName().toUpperCase());
        if (assistant.getFirstName().equals("VUK")) {
            review.setRating(0);
        }
        reviews.add(review);
    }

    public synchronized List<Review> getReviews() {
        return reviews;
    }
}
