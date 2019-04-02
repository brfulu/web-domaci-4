package common;

public class Review {
    private Assistant assistant;
    private int rating;

    public Review(Assistant assistant, int rating) {
        this.assistant = assistant;
        this.rating = rating;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
