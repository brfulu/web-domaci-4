package app;

import common.Assistant;
import common.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name").trim();
        String lastName = request.getParameter("last-name").trim();
        int grade = Integer.parseInt(request.getParameter("rating").trim());
        ReviewService.getInstance().postReview(new Review(new Assistant(firstName, lastName), grade));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head><title>Leave a review</title></head>");
        out.println("<body><h2>Review successfully saved:</h2>");
        out.println("<h3><a href=\"review-form.html\">Enter new review</a></h3>");
        out.println("<h3><a href=\"review\">See stats</a></h3>");
        out.println("</body>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Review> reviews = ReviewService.getInstance().getReviews();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head><title>Stats</title></head>");
        out.println("<body><h2>Review Stats:</h2>");
        out.println("<ul>");

        Map<Assistant, List<Integer>> ratingsMap = new HashMap<>();
        for (Review rating : reviews) {
            if (!ratingsMap.containsKey(rating.getAssistant())) {
                ratingsMap.put(rating.getAssistant(), new ArrayList<>());
            }
            ratingsMap.get(rating.getAssistant()).add(rating.getRating());
        }
        for (Assistant assistant : ratingsMap.keySet()) {
            int size = ratingsMap.get(assistant).size();
            int sum = 0;
            for (int grade : ratingsMap.get(assistant)) {
                sum += grade;
            }
            double avgGrade = sum / (double) (size);
            out.println("<li>" + assistant.getFirstName() + " " + assistant.getLastName() + ": " + avgGrade + "</li>");
        }

        out.print("</ul><br>");
        out.println("<h3><a href=\"review-form.html\">Enter new review</a></h3>");
        out.println("</body>");
        out.close();
    }
}
