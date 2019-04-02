package app;

import common.Request;
import common.Response;
import com.google.gson.Gson;
import common.Review;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReviewService {
    private static ReviewService instance = null;
    private Gson gson;

    private ReviewService() {
        this.gson = new Gson();
    }

    public static ReviewService getInstance() {
        if (instance == null)
            instance = new ReviewService();
        return instance;
    }

    public void postReview(Review review) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Request request = new Request("POST", review);
        out.writeUTF(gson.toJson(request));

        in.close();
        out.close();
        socket.close();
    }

    public List<Review> getReviews() throws IOException {
        Socket socket = new Socket("localhost", 9090);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Request request = new Request("GET", null);
        out.writeUTF(gson.toJson(request));

        String json = in.readUTF();
        Response response = gson.fromJson(json, Response.class);

        in.close();
        out.close();
        socket.close();

        return response.getReviews();
    }
}
