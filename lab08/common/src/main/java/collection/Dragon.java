package collection;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static collection.checkers.CollectionChecker.ageChecker;
import static collection.checkers.CollectionChecker.nameChecker;


/**
 * Dragon class.
 *
 * @see Color
 * @see Coordinates
 * @see DragonCave
 * @see DragonCharacter
 * @see DragonType
 */
public class Dragon implements Comparable<Dragon>, Serializable {
    private static Long freeId = 1L;
    @SerializedName("Идентификатор")
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @SerializedName("Имя")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @SerializedName("Координаты")
    private Coordinates coordinates; //Поле не может быть null

    @SerializedName("Дата инициализации")
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @SerializedName("Возраст")
    private int age; //Значение поля должно быть больше 0

    @SerializedName("Цвет")
    private Color color; //Поле может быть null

    @SerializedName("Тип")
    private DragonType type; //Поле не может быть null

    @SerializedName("Характер")
    private DragonCharacter character; //Поле может быть null

    @SerializedName("Пещера")
    private DragonCave cave; //Поле не может быть null

    private String owner;

    public Dragon(String name, Coordinates coordinates, int age, Color color, DragonType type, DragonCave cave, DragonCharacter character) {
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        setDateAuto();
    }

    public Dragon(Long id, String name, Coordinates coordinates, int age, Color color, DragonType type, DragonCave cave, DragonCharacter character) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        setDateAuto();
    }

    public static void setFreeId() {
        freeId = 2L;
    }

    @Override
    public int compareTo(Dragon other) {
        return Long.compare(this.id, other.id);
    }

    public void setIdAuto() {
        this.id = freeId;
        freeId++;
    }

    public void setDateAuto() {
        Date date = new Date();
        DateFormat formatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT,
                DateFormat.DEFAULT,
                new Locale("ru", "RU") // Русская локаль
        );
        this.creationDate = formatter.format(date);
    }

    public void setCoordinateX(Long x) {
        this.coordinates.setX(x);
    }

    public void setCoordinateY(Long y) {
        this.coordinates.setY(y);
    }


    public void setDepthCave(float depth) {
        this.cave.setDepth(depth);
    }

    public void setNumberOfTreasures(int numberOfTreasures) {
        this.cave.setNumberOfTreasures(numberOfTreasures);
    }

    public void setFreeId(Long id) {
        if (id < freeId) {
            return;
        }
        this.id = freeId;
        freeId++;
    }

    public void setId(Long id){
        this.id = id;
    }


    public void setName(String name) {
        nameChecker(name);
        this.name = name;
    }

    public void setAge(int age) {
        ageChecker(age);
        this.age = age;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Long getCoordinateX() {
        return this.coordinates.getX();
    }

    public Long getCoordinateY() {
        return this.coordinates.getY();
    }


    public String getCreationDate() {
        return this.creationDate;
    }


    public int getAge() {
        return this.age;
    }



    public Color getColor() {
        return this.color;
    }


    public DragonType getType() {
        return this.type;
    }


    public DragonCharacter getCharacter() {
        return this.character;
    }


    public DragonCave getCave() {
        return this.cave;
    }

    public Float getDepthCave() {
        return this.cave.getDepth();
    }

    public int getNumberOfTreasures() {
        return this.cave.getNumberOfTreasures();
    }

    public String getOwner(){
        return this.owner;
    }

    public String toString() {
        return "{\n" +
                "Идентификатор = " + id + "\n" +
                "Имя = " + name + "\n" +
                "Координаты = " + coordinates.toString() + "\n" +
                "Дата инициализации = " + creationDate + "\n" +
                "Возраст = " + age + "\n" +
                "Цвет = " + color + "\n" +
                "Тип = " + type + "\n" +
                "Характер = " + character + "\n" +
                "Пещера = " + cave.toString() + "\n" +
                "Владелец = " + owner + "\n" +
                "}";
    }

    public static Dragon fromString(String string){
        long id = 0;
        String name = null; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = null; //Поле не может быть null
        String creationDate = null; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        int age = 0; //Значение поля должно быть больше 0
        Color color = null; //Поле может быть null
        DragonType type = null; //Поле не может быть null
        DragonCharacter character = null; //Поле может быть null
        DragonCave cave = null;
        String owner = null;
        for (String dragon : string.split("\\R")){
            String field = dragon.split("=", 2)[0].trim();
            String value = dragon.split("=", 2)[1].trim();
            switch (field){
                case "Идентификатор" ->{
                    id = Long.parseLong(value);
                }
                case "Имя" -> {
                    name = value;
                }
                case "Координаты" -> {
                    String[] fields = value.trim().split(",");
                    coordinates = new Coordinates(Long.parseLong(fields[0].trim().split("= ")[1]), Long.parseLong(fields[1].trim().split("= ")[1].split("\\)")[0]));
                }
                case "Дата инициализации" -> {
                    creationDate = value;
                }
                case "Возраст" -> {
                    age = Integer.parseInt(value);
                }
                case "Цвет" -> {
                    color = value.equals("null") ? null : Color.valueOf(value);
                }
                case "Тип" -> {
                    System.out.println(type);
                    type = DragonType.valueOf(value);
                }
                case "Характер" -> {
                    character = value.equals("null") ? null : DragonCharacter.valueOf(value);
                }
                case "Пещера" -> {
                    String[] fields = value.trim().split(",");
                    cave = new DragonCave(Float.parseFloat(fields[0].trim().split("= ")[1]), Integer.parseInt(fields[1].trim().split("= ")[1].split("\\)")[0]));
                }
                case "Владелец" ->  {
                    owner = value;
                }
            }
        }
        Dragon dragon = new Dragon(id, name, coordinates, age, color, type, cave, character);
        dragon.setOwner(owner);
        dragon.setCreationDate(creationDate);
        return dragon;
    }
}
