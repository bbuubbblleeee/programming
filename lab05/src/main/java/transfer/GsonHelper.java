package transfer;

import collection.*;
import com.google.gson.*;
import exceptions.InvalidFileException;

import java.lang.reflect.Type;

/**
 * A class used to correctly convert a JSON object into an object of class {@link Dragon} when working with the Gson library.
 */
public class GsonHelper implements JsonDeserializer<Dragon> {
    @Override
    public Dragon deserialize(JsonElement jsonElement, Type typee, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        try {
            Long id = jsonObject.get("Идентификатор").getAsLong();
            String name = jsonObject.get("Имя").getAsString();
            int age = jsonObject.get("Возраст").getAsInt();
            Color color;
            try {
                String colorString = jsonObject.get("Цвет").getAsString();
                color = Color.valueOf(colorString);
            }
            catch (NullPointerException e){
                color = null;
            }

            DragonCharacter character;
            try {
                String characterString = jsonObject.get("Характер").getAsString();
                character = DragonCharacter.valueOf(characterString);
            }
            catch (NullPointerException e){
                character = null;
            }
            DragonType type = DragonType.valueOf(jsonObject.get("Тип").getAsString());
            JsonObject coordinatesObject = jsonObject.getAsJsonObject("Координаты");
            Coordinates coordinates = new Coordinates(coordinatesObject.get("x").getAsLong(), coordinatesObject.get("y").getAsLong());
            String creationDate = jsonObject.get("Дата инициализации").getAsString();
            JsonObject caveObject = jsonObject.getAsJsonObject("Пещера");
            DragonCave cave = new DragonCave(caveObject.get("Глубина").getAsFloat(), caveObject.get("Количество сокровищ").getAsInt());
            Dragon dragon = new Dragon(id, name, coordinates, age, color, type, cave, character);
            dragon.setCreationDate(creationDate);
            return dragon;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new InvalidFileException("Неверный файл.\nКоллекция из файла не была загружена, так как имеет неверные аргументы объекта.");
        }
    }
}
