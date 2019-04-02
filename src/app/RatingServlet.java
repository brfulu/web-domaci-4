package app;

import common.Assistant;
import common.Rating;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "RatingServlet", urlPatterns = {"/rating"})
public class RatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first-name").trim();
        String lastName = request.getParameter("last-name").trim();
        int grade = Integer.parseInt(request.getParameter("grade").trim());
        RatingService.getInstance().postRating(new Rating(new Assistant(firstName, lastName), grade));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<body><h2>Rating successfully saved:</h2>");
        out.println("<h3><a href=\"rating-form.html\">Enter new rating</a></h3>");
        out.println("<h3><a href=\"rating\">See all ratings</a></h3>");
        out.println("</body>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rating> ratings = RatingService.getInstance().getRatings();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<body><h2>Ratings:</h2>");
        out.println("<ul>");

        Map<Assistant, List<Integer>> ratingsMap = new HashMap<>();
        for (Rating rating : ratings) {
            if (!ratingsMap.containsKey(rating.getAssistant())) {
                ratingsMap.put(rating.getAssistant(), new ArrayList<>());
            }
            ratingsMap.get(rating.getAssistant()).add(rating.getGrade());
        }
        for (Assistant assistant : ratingsMap.keySet()) {
            int sum = 0;
            int size = ratingsMap.get(assistant).size();
            for (int grade : ratingsMap.get(assistant)) {
                sum += grade;
            }
            double avgGrade = sum / (double)(size);
            out.println("<li>" + assistant.getFirstName() + " " + assistant.getLastName() + ": " + avgGrade + "</li>");
        }

        out.print("</ul><br>");
        out.println("<h3><a href=\"rating-form.html\">Enter new rating</a></h3>");
        out.println("</body>");
        out.close();
    }
}
