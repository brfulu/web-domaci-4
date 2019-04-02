package database.server;

import java.io.IOException;

public class DBServerMain {
    public static void main(String[] args) {
        try {
            Thread server = new Thread(new DBServer(9090, 41));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
