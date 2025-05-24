package users;

public class User {
    private static String login = null;
    private static String password = null;

    public User(String login, String password){
        User.login = login;
        User.password = password;
    }

    public static String getLogin() {
        return login;
    }
}
