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
            Long id = jsonObject.get("id").getAsLong();
            String name = jsonObject.get("name").getAsString();
            int age = jsonObject.get("age").getAsInt();

            Color color = null;
            DragonCharacter character = null;
            try {
                color = Color.valueOf(jsonObject.get("color").getAsString());
                character = DragonCharacter.valueOf(jsonObject.get("character").getAsString());
            } catch (Exception ignored) {}
            DragonType type = DragonType.valueOf(jsonObject.get("type").getAsString());
            JsonObject coordinatesObject = jsonObject.getAsJsonObject("coordinates");
            Coordinates coordinates = new Coordinates(coordinatesObject.get("x").getAsLong(), coordinatesObject.get("y").getAsLong());
            JsonObject caveObject = jsonObject.getAsJsonObject("cave");
            DragonCave cave = new DragonCave(caveObject.get("depth").getAsFloat(), caveObject.get("numberOfTreasures").getAsInt());
            Dragon dragon = new Dragon(name, coordinates, age, color, type, cave, character);
            dragon.setId(id);
            return dragon;
        }
        catch (Exception e){
            throw new InvalidFileException("Invalid script.\nThe collection from the file wasn't uploaded as it has wrong arguments of the object.");
        }
    }
}
