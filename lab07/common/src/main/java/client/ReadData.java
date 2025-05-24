package client;

import collection.*;
import collection.checkers.CollectionChecker;
import exceptions.InvalidFileException;
import exceptions.WrongArgumentException;
import io.ConsoleReader;
import io.FileReader;
import io.MyReader;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The class is responsible for inputting, checking and validating the arguments required to create an object of the lass {@link Dragon}.
 * Can read data from the console or from a file.
 **/
public class ReadData implements Serializable, AutoCloseable {
    public final MyReader myReader;
    private final boolean inScript;


    public ReadData(String filePath) {
        this.inScript = true;
        myReader = new FileReader(filePath);
    }

    public ReadData() {
        this.inScript = false;
        myReader = new ConsoleReader();
    }

    public Dragon get() throws InterruptedException {
        String name = input("имя", CollectionChecker::nameChecker, Function.identity());
        int age = input("возраст", CollectionChecker::ageChecker, Integer::valueOf);
        Color color = input("цвет %s".formatted(
                Arrays.toString(Color.values())), null, s -> Color.valueOf(s.toUpperCase()));
        DragonType type = input("тип %s".formatted(
                Arrays.toString(DragonType.values())), CollectionChecker::typeChecker, s -> DragonType.valueOf(s.toUpperCase()));

        DragonCharacter character = input("характер %s".formatted(
                Arrays.toString(DragonCharacter.values())), null, s -> DragonCharacter.valueOf(s.toUpperCase()));

        long x = input("координата х", CollectionChecker::xChecker, Long::valueOf);
        long y = input("координата у", CollectionChecker::yChecker, Long::valueOf);
        Coordinates coordinates = new Coordinates(x, y);

        Float depth = Float.parseFloat(input("глубина пещеры", CollectionChecker::depthChecker, Function.identity()));
        int numberOfTreasures = input("количество сокровищ", CollectionChecker::numberOfTreasuresChecker, Integer::valueOf);
        DragonCave cave = new DragonCave(depth, numberOfTreasures);
        return new Dragon(name, coordinates, age, color, type, cave, character);
    }

    private <K> K input(
            final String fieldName,
            final Consumer<K> action,
            final Function<String, K> parser
    ) throws InterruptedException, InvalidFileException {
        while (true) {
            try {
                String line = myReader.readLine(fieldName);
                if (line == null || line.equals("return")) throw new InterruptedException("Возврат вызван.");
                K result = null;
                if (!line.isBlank()) {
                    try {
                        result = parser.apply(line);
                    } catch (NumberFormatException e) {
                        throw new WrongArgumentException("Недопустимое значение.");
                    }
                }
                if (action != null) {
                    action.accept(result);
                }
                return result;
            } catch (InterruptedException e) {
                throw e;
            } catch (WrongArgumentException | IllegalArgumentException | NullPointerException ex) {
                if (inScript) {
                    throw new InvalidFileException("Недопустимый файл скрипта.");
                }
                System.out.println(ex.getMessage());
            } catch (Exception exx) {
                System.out.println(exx.getMessage());
            }

        }
    }

    @Override
    public void close() throws Exception {
        this.myReader.close();
    }
}
