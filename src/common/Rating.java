package common;

public class Rating {
    private Assistant assistant;
    private int grade;

    public Rating(Assistant assistant, int grade) {
        this.assistant = assistant;
        this.grade = grade;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
