package collection.checkers;

import collection.DragonType;
import exceptions.WrongArgumentException;
/*
    Класс содержит методы для проверки всех необходимых значений аргументов объекта класса {@link Dragon}.
    <p>
    Проверки включают:
    <ul>
        <li> {@link nameChecker} - Имя не может быть null или пустой строкой.
        <li> {@link ageChecker} - Возраст должен быть больше 0.
        <li> {@link xChecker} - Координата x должна быть больше -28.
        <li> {@link yChecker} - Координата y не может быть null.
        <li> {@link typeChecker} - Тип не может быть null.
        <li> {@link numberOfTreasuresChecker} - Количество сокровищ должно быть больше 0.
    </ul>
    <p>
    Наследуется от {@ArgumentChecker} и использует его базовые проверки.
    @throws WrongArgumentException при некорректном аргументе.
 */
public class CollectionChecker extends ArgumentChecker{
    public static void nameChecker(String name) throws WrongArgumentException {
        nullChecker(name);
        argumentChecker(!name.isEmpty(), "Argument can't be empty.");
    }

    public static void ageChecker(Integer age) throws WrongArgumentException{
        nullChecker(age);
        argumentChecker(age > 0, "Invalid value.\nExpected value > 0.");
    }

    public static void xChecker(Long x) throws WrongArgumentException{
        nullChecker(x);
        argumentChecker(x > - 28, "Invalid value.\nExpected value > -28.");
    }
    public static void yChecker(Long y) throws WrongArgumentException{
        nullChecker(y);
    }

    public static void typeChecker(DragonType type) throws WrongArgumentException{
        nullChecker(type);
    }

    public static void numberOfTreasuresChecker(int numberOfTreasures) throws WrongArgumentException{
        argumentChecker(numberOfTreasures > 0, "Invalid value.\nExpected value > 0.");
    }
}
