package client;

import collection.*;

import collection.checkers.CollectionChecker;
import exceptions.InvalidFileException;
import exceptions.WrongArgumentException;
import io.ConsoleReader;
import io.FileReader;
import io.MyReader;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The class is responsible for inputting, checking and validating the arguments required to create an object of the lass {@link Dragon}.
 * Can read data from the console or from a file.
 **/
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
}
