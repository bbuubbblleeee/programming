package dbConnection;

import exceptions.InvalidFileException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final String URL;
    private final String user;
    private final String password;

    public DbConnection(String URL, String user, String password){
        this.URL = URL;
        this.user = user;
        this.password = password;
    }
    public Connection connect() {
        try{
            return DriverManager.getConnection(URL, user, password);
        }
        catch (SQLException sqlException){
            throw new InvalidFileException("Не удалось подключиться к базе данных.");
        }
    }
}
