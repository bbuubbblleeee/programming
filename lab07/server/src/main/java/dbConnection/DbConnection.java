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
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", user, password);
            return connection;
        }
        catch (SQLException sqlException){
            throw new InvalidFileException("Не удалось подключиться к базе данных.");
            //TODO поменять вид ошибки или че нибудь сделать с этим короче
        }
    }
}
