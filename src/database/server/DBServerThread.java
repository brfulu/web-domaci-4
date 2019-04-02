package database.server;

import com.google.gson.Gson;
import common.Review;
import common.Request;
import common.Response;
import database.model.Store;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DBServerThread implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Store store;
    private Gson gson;

    public DBServerThread(Socket socket, Store store) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.store = store;
        this.gson = new Gson();
    }

    @Override
    public void run() {
        try {
            String json = in.readUTF();
            Request request = gson.fromJson(json, Request.class);
            if (request.getType().equals("POST")) {
                System.out.println("POST");
                store.addReview(request.getReview());
            } else if (request.getType().equals("GET")) {
                System.out.println("GET");
                List<Review> reviews = store.getReviews();
                Response response = new Response("OK", reviews);
                out.writeUTF(gson.toJson(response));
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
