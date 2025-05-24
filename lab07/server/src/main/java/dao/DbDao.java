package dao;

import collection.*;
import dbConnection.DbConnection;
import exceptions.DbErrorException;
import exceptions.LoginUserException;
import users.Password;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class DbDao implements DAO{
    private Connection connection = new DbConnection("jdbc:postgresql://localhost:5433/studs", "s489568", "ucXGL33fhMIf4P30").connect();
    private String table = "dragon";

    @Override
    public DateAndDragons get() {
        return readDb();
    }

    @Override
    public void save(Set<Dragon> dragons) {
        //Больше не используется
//        try {
//            System.out.println("Let's go");
//            writeInDb(dragons);
//        }
//        catch (Exception e){
//            throw new DbErrorException(e.getMessage());
//        }
    }

    public long add(Dragon dragon, String login) {
        return writeInDb(dragon, login);
    }

    public long add_if_max(Dragon dragon, long lastId, String login)  {
        long id = writeInDb(dragon, login);
        if (id <= lastId){
            remove("id = " + dragon.getId(), login);
            throw new DbErrorException("Дракон не был добавлен в коллекцию, так как его значение меньше значения наибольшего элемента коллекции.");
        }
        return id;
    }

    public void update(long id, Dragon dragon, String login){
        updateInDb(id, dragon, login);
    }

    public void clear(String login){
        clearDb(login);
    }

    public long remove(String condition, String login){
        String[] condParts = condition.trim().split("\\s+");
        System.out.println(condParts[0]);
        System.out.println(condParts[1]);
        System.out.println(condParts[2]);


        if (condParts[0].equals("id") && condParts[1].matches("[<>]")){
            removeFromDb("id = " + condParts[2], login);
        }
        return removeFromDb(condition, login);
    }

    public void checkUser(String login, String password){
        try(PreparedStatement statement = connection.prepareStatement("SELECT passwordHashed, salt FROM users WHERE login = ?")){
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String passwordHashedUser = resultSet.getString("passwordHashed");
                byte[] salt = resultSet.getBytes("salt");
                String passwordHashed = new Password().getPasswordHashed(password, salt);
                if (passwordHashed.equals(passwordHashedUser)){
                    return;
                }
                throw new DbErrorException("Неверный пароль, попробуйте выполнить вход повторно.");
            }
            throw new DbErrorException("Пользователя с таким логином не существует. Зарегистрируйтесь, прописав sign_up {логин} {пароль}");
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbErrorException("Неуспешная проверка данных пользователя.");
        }
    }

    public void signUpUser(String login, String passwordNew){
        try(PreparedStatement loginStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, passwordHashed, salt) VALUES (?, ?, ?)")){
            loginStatement.setString(1, login);
            ResultSet resultSet = loginStatement.executeQuery();
            if (resultSet.next()){
                throw new LoginUserException("Такой логин уже существует, придумайте другой и повторите ввод.");
            }
            statement.setString(1, login);
            Password password = new Password();
            String passwordHashed = password.getPasswordHashed(passwordNew);
            byte[] salt = password.getSalt();
            statement.setString(2, passwordHashed);
            statement.setBytes(3, salt);
            statement.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbErrorException("Неуспешная регистрация пользователя.");
        }
    }

    private DateAndDragons readDb() {
        //TODO подумать что делать с датой инициализации, пока что стоит текущая дата
        TreeSet<Dragon> dragons = new TreeSet<>();
        try (Statement statement = connection.createStatement();
             ResultSet dragonSet = statement.executeQuery("SELECT * FROM dragon")){
            while (dragonSet.next()){
                PreparedStatement coordinates = connection.prepareStatement("SELECT x, y FROM coordinates WHERE id = ?");
                PreparedStatement cave = connection.prepareStatement("SELECT depth, numberOfTreasures FROM dragon_cave WHERE id = ?");

                Long id = dragonSet.getLong("id");
                String name = dragonSet.getString("name");
                String creationDate = dragonSet.getString("creationDate");
                int age = dragonSet.getInt("age");

                String colorString = dragonSet.getString("color");
                Color color = colorString == null ? null : Color.valueOf(colorString);

                String dragonTypeString = dragonSet.getString("dragonType");
                DragonType dragonType = DragonType.valueOf(dragonTypeString);

                String dragonCharacterString = dragonSet.getString("dragonCharacter");
                DragonCharacter dragonCharacter = dragonCharacterString == null ? null : DragonCharacter.valueOf(dragonCharacterString);

                long coordinatesId = dragonSet.getLong("coordinatesId");
                coordinates.setLong(1, coordinatesId);
                ResultSet coordinateSet = coordinates.executeQuery();
                Coordinates dragonCoordinates = null;
                if (coordinateSet.next()) {
                    long x = coordinateSet.getLong("x");
                    long y = coordinateSet.getLong("y");
                    dragonCoordinates = new Coordinates(x, y);
                }
                coordinateSet.close();
                coordinates.close();

                long dragonCaveId = dragonSet.getLong("dragonCaveId");
                cave.setLong(1, dragonCaveId);
                ResultSet caveSet = cave.executeQuery();
                DragonCave dragonCave = null;
                if (caveSet.next()) {
                    float depth = caveSet.getFloat("depth");
                    int numberOfTreasures = caveSet.getInt("numberOfTreasures");
                    dragonCave = new DragonCave(depth, numberOfTreasures);
                }
                caveSet.close();
                cave.close();

                Dragon dragon = new Dragon(id, name, dragonCoordinates, age, color, dragonType, dragonCave, dragonCharacter);
                System.out.println(dragon);
                dragon.setCreationDate(creationDate);
                dragons.add(dragon);
            }
            return new DateAndDragons(DateFormat.getDateTimeInstance().format(new Date()), dragons);
        }
        catch (SQLException sqlException){
            throw new DbErrorException("Ошибка считывания данных из базы данных.");
        }
    }

    private long removeFromDb(String condition, String login){
        try(
        Statement statement = connection.createStatement();
        ){
            String sql = "DELETE FROM dragon WHERE userLogin = '" + login + "' AND "  + condition;
            long rows = statement.executeUpdate(sql);
            return rows;
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            throw new DbErrorException("Ошибка удаления дракона из базы данных.");
        }
    }

    private void clearDb(String login) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM dragon WHERE userLogin = ?")){
            statement.setString(1, login);
            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new DbErrorException("В коллекции отсутствуют объекты, принадлежащие данному пользователю. Коллекция не была очищена");
            }
        }
        catch (SQLException sqlException){
            throw new DbErrorException("Ошибка очистки базы данных.");
        }
    }

    private long writeInDb(Dragon dragon, String login) {

        try(PreparedStatement dragonStatement = connection.prepareStatement(
                "INSERT INTO dragon (name, coordinatesId, creationDate, age, color, dragonType, dragonCharacter, dragonCaveId, userLogin) " +
                        "VALUES (?, ?, ?, ?, ?::COLOR, ?::DRAGON_TYPE, ?::DRAGON_CHARACTER, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            Statement statement = connection.createStatement()){
            PreparedStatement coordinates = connection.prepareStatement("INSERT INTO coordinates (x, y) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement cave = connection.prepareStatement("INSERT INTO dragon_cave (depth, numberOfTreasures) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            coordinates.setLong(1, dragon.getCoordinates().getX());
            coordinates.setLong(2, dragon.getCoordinates().getY());
            coordinates.executeUpdate();
            long coordinatesId = getFieldId(coordinates);


            cave.setFloat(1, dragon.getCave().getDepth());
            cave.setInt(2, dragon.getCave().getNumberOfTreasures());
            cave.executeUpdate();
            long dragonCaveId = getFieldId(cave);

            dragonStatement.setString(1, dragon.getName());
            dragonStatement.setLong(2, coordinatesId);
            dragonStatement.setString(3, dragon.getCreationDate());
            dragonStatement.setInt(4, dragon.getAge());

            if (dragon.getColor() != null) {
                dragonStatement.setString(5, dragon.getColor().toString());
            } else {
                dragonStatement.setNull(5, java.sql.Types.OTHER); // Для ENUM

            }
            dragonStatement.setString(6, dragon.getType().toString());

            if (dragon.getCharacter() != null) {
                dragonStatement.setString(7, dragon.getCharacter().toString());
            } else {
                dragonStatement.setNull(7, java.sql.Types.OTHER); // Для ENUM
            }

            dragonStatement.setLong(8, dragonCaveId);
            System.out.println(login);
            dragonStatement.setString(9, login);
            dragonStatement.executeUpdate();
            return getFieldId(dragonStatement);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            throw new DbErrorException("Ошибка записи данных в базу данных.");
        }


    }

    private void updateInDb(long id, Dragon dragon, String login){
        try(PreparedStatement dragonStatement = connection.prepareStatement(
                "UPDATE dragon " +
                        "SET name = ?, coordinatesId = ?, age = ?, color = ?::COLOR, dragonType = ?::DRAGON_TYPE, dragonCharacter = ?::DRAGON_CHARACTER, dragonCaveId = ? " +
                        "WHERE id = ? AND userLogin = ?");
            Statement statement = connection.createStatement()){
            PreparedStatement coordinates = connection.prepareStatement("UPDATE coordinates SET x = ?, y = ? WHERE id = ?");
            PreparedStatement cave = connection.prepareStatement("UPDATE dragon_cave SET depth = ?, numberOfTreasures = ? WHERE id = ?");
            PreparedStatement findDragon = connection.prepareStatement("SELECT coordinatesId, dragonCaveId FROM dragon WHERE id = " + id);

            ResultSet foundDragon = findDragon.executeQuery();
            if (!foundDragon.next()){
                throw new DbErrorException("Дракон с таким id не был найден.");
            }

            long coordinatesId = foundDragon.getLong("coordinatesId");
            long dragonCaveId = foundDragon.getLong("dragonCaveId");
            coordinates.setLong(1, dragon.getCoordinates().getX());
            coordinates.setLong(2, dragon.getCoordinates().getY());
            coordinates.setLong(3, coordinatesId);
            coordinates.executeUpdate();


            cave.setFloat(1, dragon.getCave().getDepth());
            cave.setInt(2, dragon.getCave().getNumberOfTreasures());
            cave.setLong(3, dragonCaveId);
            cave.executeUpdate();

            dragonStatement.setString(1, dragon.getName());
            dragonStatement.setLong(2, coordinatesId);
            dragonStatement.setInt(3, dragon.getAge());

            if (dragon.getColor() != null) {
                dragonStatement.setString(4, dragon.getColor().toString());
            } else {
                dragonStatement.setNull(4, java.sql.Types.OTHER); // Для ENUM

            }

            if (dragon.getCharacter() != null) {
                dragonStatement.setString(6, dragon.getCharacter().toString());
            } else {
                dragonStatement.setNull(6, java.sql.Types.OTHER); // Для ENUM
            }


            dragonStatement.setString(5, dragon.getType().toString());


            dragonStatement.setLong(7, dragonCaveId);

            dragonStatement.setLong(8, id);
            dragonStatement.setString(9, login);
            if (dragonStatement.executeUpdate() == 0){
                throw new DbErrorException("Объект не принадлежит данному пользователю, его модификация запрещена.");
            };


        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            throw new DbErrorException("Ошибка обновления данных в базе данных.");
        }
    }

    private Long getFieldId(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        }
        throw new SQLException();
    }

    //USELESS TODO
//    private void writeInDb(Set<Dragon> dragons) throws SQLException {
//
//        try(PreparedStatement dragonStatement = connection.prepareStatement(
//                "INSERT INTO dragon (name, coordinatesId, creationDate, age, color, dragonType, dragonCharacter, dragonCaveId) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//            Statement statement = connection.createStatement();
//            ResultSet delete = statement.executeQuery("DELETE FROM dragon");
//        ){
//            System.out.println(dragons.size());
//            for (Dragon dragon : dragons){
//                System.out.println(1);
//                PreparedStatement coordinates = connection.prepareStatement("INSERT INTO coordinates (x, y) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
//                PreparedStatement cave = connection.prepareStatement("INSERT INTO dragon_cave (depth, numberOfTreasures) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
//
//                coordinates.setLong(1, dragon.getCoordinates().getX());
//                coordinates.setLong(2, dragon.getCoordinates().getY());
//                coordinates.executeUpdate();
//                long coordinatesId = getFieldId(coordinates);
//                System.out.println(coordinatesId);
//
//
//                cave.setFloat(1, dragon.getCave().getDepth());
//                cave.setInt(2, dragon.getCave().getNumberOfTreasures());
//                cave.executeUpdate();
//                long dragonCaveId = getFieldId(cave);
//                System.out.println(dragonCaveId);
//
//
//                dragonStatement.setString(1, dragon.getName());
//                dragonStatement.setLong(2, coordinatesId);
//                dragonStatement.setString(3, dragon.getCreationDate());
//                dragonStatement.setInt(4, dragon.getAge());
//                dragonStatement.setString(5, dragon.getColor().toString());
//                dragonStatement.setString(6, dragon.getType().toString());
//                dragonStatement.setString(7, dragon.getCharacter().toString());
//                dragonStatement.setLong(8, dragonCaveId);
//                dragonStatement.executeUpdate();
//            }
//        }
//        catch (SQLException sqlException){
//            throw new DbErrorException("Ошибка записи данных в базу данных.");
//        }
//
//
//    }

}
