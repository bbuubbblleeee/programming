package collection;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
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

    public Dragon(String name, Coordinates coordinates, int age, Color color, DragonType type, DragonCave cave, DragonCharacter character) {
        setIdAuto();
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
        setId(id);
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
        freeId = 1L;
    }

    public static Long getFreeId() {
        return freeId;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id < freeId) {
            return;
        }
        this.id = freeId;
        freeId++;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        nameChecker(name);
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        ageChecker(age);
        this.age = age;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DragonType getType() {
        return this.type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getCharacter() {
        return this.character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public DragonCave getCave() {
        return this.cave;
    }

    public String toString() {
        return "Идентификатор = " + id + "\n" +
                "Имя = " + name + "\n" +
                "Координаты = " + coordinates.toString() + "\n" +
                "Дата инициализации = " + creationDate + "\n" +
                "Возраст = " + age + "\n" +
                "Цвет = " + color + "\n" +
                "Тип = " + type + "\n" +
                "Характер = " + character + "\n" +
                "Пещера = " + cave.toString() + "\n";
    }
}
