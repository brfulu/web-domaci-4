package database.server;

import common.Rating;
import database.model.Store;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBServer implements Runnable {
    private ServerSocket listener;
    private ExecutorService pool;
    private Store store;

    public DBServer(int port, int poolSize) throws IOException {
        this.listener = new ServerSocket(port);
        this.pool = Executors.newFixedThreadPool(poolSize);
        this.store = new Store();
    }

    @Override
    public void run() {
        System.out.println("Database server is running...");
        while (true) {
            try {
                pool.execute(new DBServerThread(listener.accept(), store));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
