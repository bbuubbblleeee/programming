package transfer;

import collection.*;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Класс, служащий для корректного преобразования JSON-объекта в объект класса Dragon при работе с библиотекой Gson
 */
public class GsonHelper implements JsonDeserializer<Dragon> {
    @Override
    public Dragon deserialize(JsonElement jsonElement, Type typee, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        int age = jsonObject.get("age").getAsInt();

        Color color = null;
        try {
            color = Color.valueOf(jsonObject.get("color").getAsString());
        }
        catch (Exception ignored){}

        DragonCharacter character = null;
        try{
            character = DragonCharacter.valueOf(jsonObject.get("character").getAsString());
        }
        catch (Exception ignored){}

        DragonType type = DragonType.valueOf(jsonObject.get("type").getAsString());
        JsonObject coordinatesObject = jsonObject.getAsJsonObject("coordinates");
        Coordinates coordinates = new Coordinates(coordinatesObject.get("x").getAsLong(), coordinatesObject.get("y").getAsLong());
        JsonObject caveObject = jsonObject.getAsJsonObject("cave");
        DragonCave cave = new DragonCave(caveObject.get("depth").getAsFloat(), caveObject.get("numberOfTreasures").getAsInt());
        Dragon dragon = new Dragon(name, coordinates, age, color, type, cave, character);
        dragon.setId(id);
        return dragon;
    }
}
