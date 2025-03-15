package client;

import collection.*;

import collection.checkers.CollectionChecker;
import exceptions.InvalidFileException;
import exceptions.WrongArgumentException;
import io.ConsoleReader;
import io.FileReader;
import io.MyReader;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/*
    Класс отвечает за ввод, проверку и валидацию аргументов, необходимых для создания объекта класса {@link Dragon}.
    <p>
    В зависимости от режима работы может считывать данные из консоли или из файла.
    @return объект класса {@link Dragon}.
    @throws InterruptException при прекращении ввода пользователем.
 */
public class ReadData {
    public final MyReader myReader;
    private final boolean inScript;

    public ReadData(String filePath){
        this.inScript = true;
        myReader = new FileReader(filePath);
    }
    public ReadData(){
        this.inScript = false;
        myReader = new ConsoleReader();
    }

    public Dragon get() throws InterruptedException {
        String name = input("name", CollectionChecker::nameChecker, Function.identity()) ;
        int age = input("age", CollectionChecker::ageChecker, Integer::valueOf) ;
        Color color = input("color %s".formatted(
                Arrays.toString(Color.values())), null , s -> Color.valueOf(s.toUpperCase()));
        DragonType type = input("type %s".formatted(
                Arrays.toString(DragonType.values())), CollectionChecker::typeChecker, s -> DragonType.valueOf(s.toUpperCase()));

        DragonCharacter character = input("character %s".formatted(
                Arrays.toString(DragonCharacter.values())), null, s-> DragonCharacter.valueOf(s.toUpperCase()));

        long x = input("coordinate x", CollectionChecker::xChecker, Long::valueOf) ;
        long y = input("coordinate y", CollectionChecker::yChecker, Long::valueOf);
        Coordinates coordinates = new Coordinates(x, y);

        Float depth = input("depth of the cave", null, Float::valueOf) ;
        int numberOfTreasures = input("number of treasures in the cave", CollectionChecker::numberOfTreasuresChecker, Integer::valueOf) ;
        DragonCave cave = new DragonCave(depth, numberOfTreasures);

        return new Dragon(name, coordinates, age, color, type, cave, character);
    }

    private <K> K input(
            final String fieldName,
            final Consumer<K> action,
            final Function<String, K> parser
    ) throws InterruptedException {
        while (true) {
            try {
                String line = myReader.readLine(fieldName);
                if (line == null || line.equals("return")) throw new InterruptedException("called return");
                K result = line.isBlank() ? null : parser.apply(line);
                if (action != null) {
                    action.accept(result);
                }
                return result;
            } catch (InterruptedException e) {
                throw e;
            } catch (WrongArgumentException | IllegalArgumentException | NullPointerException ex) {
                if (inScript) {
                    throw new InvalidFileException("Invalid script File.");
                }
                System.out.println(ex.getMessage());
            }
        }
    }









//    public Dragon readDragon(){
//        String name = readName();
//        int age = readAge();
//        Color color = readColor();
//        DragonCharacter character = readCharacter();
//        DragonType type = readType();
//        Coordinates coordinates = readCoordinates();
//        DragonCave cave = readCave();
//        return new Dragon(name, coordinates, age, color, type, cave, character);
//    }
//
//    public Color readColor() throws InvalidFileException{
//        StringBuilder values = getEnumValues(Color.class);
//        while (true) {
//            try{
//                String string = myReader.readLine("color (select one of the constants: " + values + ", or leave the field blank)");
//                if (string == null || string.isEmpty()) {
//                    return null;
//                }
//                return Color.valueOf(string.toUpperCase());
//            }
//            catch (IllegalArgumentException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid color of the object in the file.");
//                }
//                System.out.println("Invalid value.\nExpected null or one of the constants: " + values + ".");
//            }
//
//        }
//    }
//    public DragonCharacter readCharacter() throws InvalidFileException{
//        StringBuilder values = getEnumValues(DragonCharacter.class);
//        while (true) {
//            try{
//                String string = myReader.readLine("character (select one of the constants: " + values + ", or leave the field blank)");
//                if (string == null || string.isEmpty()) {
//                    return null;
//                }
//                return DragonCharacter.valueOf(string.toUpperCase());
//            }
//            catch (IllegalArgumentException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid character of the object in the file.");
//                }
//                System.out.println("Invalid value.\nExpected null or one of the constants: " + values + ".");
//            }
//
//        }
//    }
//
//    public DragonType readType() throws InvalidFileException{
//        StringBuilder values = getEnumValues(DragonType.class);
//        while (true) {
//            try{
//                String string = myReader.readLine("type (select one of the constants: " + values + ")");
//                if (string.isEmpty()) {
//                    throw new WrongArgumentException("argument can't be null.");
//                }
//                return DragonType.valueOf(string.toUpperCase());
//            }
//            catch (WrongArgumentException b){
//                if (inScript){
//                    throw new InvalidFileException("Invalid type of the object in the file.");
//                }
//                System.out.println(b.getMessage());
//            }
//            catch (IllegalArgumentException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid type of the object in the file.");
//                }
//                System.out.println("Invalid value.\nExpected one of the constants: " + values + ".");
//            }
//
//        }
//    }
//
//    public String readName() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("name");
//                nameChecker(string);
//                return string;
//            }
//            catch (WrongArgumentException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid name of the object in the file.");
//                }
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//    public Coordinates readCoordinates() throws InvalidFileException{
//        Coordinates coordinates;
//        while (true){
//            try{
//                Long x = readX();
//                Long y = readY();
//                coordinates = new Coordinates(x, y);
//                coordinatesChecker(coordinates);
//                break;
//            }
//            catch (WrongArgumentException e){
//                System.out.println(e.getMessage());
//            }
//        }
//        return coordinates;
//
//    }
//
//    public int readAge() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("age");
//                int age = Integer.parseInt(string);
//                ageChecker(age);
//                return age;
//            }
//            catch (NumberFormatException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid age of the object in the file.");
//                }
//                System.out.println("Invalid type of value. Expected int.");
//            }
//            catch (WrongArgumentException b){
//                if (inScript){
//                    throw new InvalidFileException("Invalid age of the object in the file.");
//                }
//                System.out.println(b.getMessage());
//            }
//        }
//    }
//
//    public DragonCave readCave() throws InvalidFileException{
//        DragonCave cave;
//        while (true){
//            try{
//                Float depth = readDepth();
//                int numberOfTreasures = readNumberOfTreasures();
//                cave = new DragonCave(depth, numberOfTreasures);
//                caveChecker(cave);
//                break;
//            }
//            catch (WrongArgumentException e){
//                System.out.println(e.getMessage());
//            }
//        }
//        return cave;
//
//    }
//
//    public Long readX() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("coordinate x");
//                Long x = Long.parseLong(string);
//                xChecker(x);
//                return x;
//            }
//            catch (NumberFormatException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid coordinate x of the object in the file.");
//                }
//                System.out.println("Invalid type of value. Expected Long.");
//            }
//            catch (WrongArgumentException b){
//                if (inScript){
//                    throw new InvalidFileException("Invalid coordinate x of the object in the file.");
//                }
//                System.out.println(b.getMessage());
//            }
//        }
//    }
//
//    public Long readY() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("coordinate y");
//                return Long.parseLong(string);
//            }
//            catch (NumberFormatException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid coordinate y of the object in the file.");
//                }
//                System.out.println("Invalid type of value. Expected Long.");
//            }
//        }
//    }
//
//    public Float readDepth() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("depth of the cave");
//                return Float.parseFloat(string);
//            }
//            catch (NumberFormatException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid depth of the object's cave in the file.");
//                }
//                System.out.println("Invalid type of value. Expected Float.");
//            }
//            catch (WrongArgumentException b){
//                if (inScript){
//                    throw new InvalidFileException("Invalid depth of the object's cave in the file.");
//                }
//                System.out.println(b.getMessage());
//            }
//        }
//    }
//
//    public int readNumberOfTreasures() throws InvalidFileException{
//        while (true) {
//            try{
//                String string = myReader.readLine("number of treasures in the cave");
//                int numberOfTreasures = Integer.parseInt(string);
//                numberOfTreasuresChecker(numberOfTreasures);
//                return numberOfTreasures;
//            }
//            catch (NumberFormatException e){
//                if (inScript){
//                    throw new InvalidFileException("Invalid number of treasures in the object's cave in the file.");
//                }
//                System.out.println("Invalid type of value. Expected int.");
//            }
//            catch (WrongArgumentException b){
//                if (inScript){
//                    throw new InvalidFileException("Invalid number of treasures in the object's cave in the file.");
//                }
//                System.out.println(b.getMessage());
//            }
//        }
//    }
//
//
//    public <E extends Enum<E>> StringBuilder getEnumValues (Class <E> enumClass){
//        E[] allValues = enumClass.getEnumConstants();
//        StringBuilder values = new StringBuilder();
//        for (E value : allValues){
//            values.append(value.name().toLowerCase()).append(", ");
//        }
//        values.delete(values.length() - 2, values.length());
//        return values;
//    }

}
