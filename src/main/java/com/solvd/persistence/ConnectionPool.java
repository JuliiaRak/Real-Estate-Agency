package com.solvd.persistence;

import com.solvd.domain.exceptions.CreateConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private volatile static ConnectionPool instance;
    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();

    private ConnectionPool(int size) {
        for (int i = 0; i < size; i++) {
            try {
                connections.add(DriverManager.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD));
            } catch (SQLException e) {
                throw new CreateConnectionException(e);
            }
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(Config.POOL_SIZE);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = connections.poll();
        while (connection == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            connection = connections.poll();
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notify();
    }
}
