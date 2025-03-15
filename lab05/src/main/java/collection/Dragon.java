package collection;


import static collectionManager.CollectionManager.dragons;

import java.util.Date;

import static collection.checkers.CollectionChecker.*;
/**
 * Dragon class.
 *
 * @see Color
 * @see Coordinates
 * @see DragonCave
 * @see DragonCharacter
 * @see DragonType
 */
public class Dragon implements Comparable<Dragon> {
    private static Long freeId = 1L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
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
        setDate();
    }

    public static void setFreeId(){
        freeId = 1L;
    }

    @Override
    public int compareTo(Dragon other) {
        return Long.compare(this.id, other.id);
    }

    public void setIdAuto() {
        for (Dragon dragon : dragons) {
            if (dragon.getId().equals(freeId)) {
                freeId++;
            }
        }
        this.id = freeId;
        freeId++;
    }

    public void setId(Long id) {
        for (Dragon dragon : dragons) {
            if (dragon.getId().equals(id)) {
                id++;
            }
        }
        this.id = id;
        freeId--;
    }


    public void setDate() {
        this.creationDate = new Date();
    }

    public void setName(String name) {
        nameChecker(name);
        this.name = name;
    }

    public void setAge(int age) {
        ageChecker(age);
        this.age = age;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
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


    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public java.util.Date getCreationDate() {
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

    public String toString() {
        return "{" +
                "id = " + id + "\n" +
                "name = " + name + "\n" +
                "coordinates : " + coordinates.toString() + "\n" +
                "creation date = " + creationDate + "\n" +
                "age = " + age + "\n" +
                "color = " + color + "\n" +
                "type = " + type + "\n" +
                "character = " + character + "\n" +
                "cave : " + cave.toString() + "\n" +
                "}";
    }
}
