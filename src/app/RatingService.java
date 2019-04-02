package app;

import common.Rating;
import common.Request;
import common.Response;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class RatingService {
    private static RatingService instance = null;
    private Gson gson;

    private RatingService() {
        this.gson = new Gson();
    }

    public static RatingService getInstance() {
        if (instance == null)
            instance = new RatingService();
        return instance;
    }

    public void postRating(Rating rating) throws IOException {
        Socket socket = new Socket("localhost", 9090);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Request request = new Request("POST", rating);
        out.writeUTF(gson.toJson(request));

        in.close();
        out.close();
        socket.close();
    }

    public List<Rating> getRatings() throws IOException {
        System.out.println("Get ratings");
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

        return response.getRatings();
    }
}
