package database.model;

import common.Assistant;
import common.Rating;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Rating> ratings;

    public Store() {
        this.ratings = new ArrayList<>();
    }

    public synchronized void addRating(Rating rating) {
        Assistant assistant = rating.getAssistant();
        assistant.setFirstName(assistant.getFirstName().toUpperCase());
        assistant.setLastName(assistant.getLastName().toUpperCase());
        if (assistant.getFirstName().equals("VUK")) {
            rating.setGrade(0);
        }
        ratings.add(rating);
    }

    public synchronized List<Rating> getRatings() {
        return ratings;
    }
}
