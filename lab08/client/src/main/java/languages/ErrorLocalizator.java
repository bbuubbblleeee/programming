package languages;

import java.util.Map;

public class ErrorLocalizator extends Localizator{
    private final Map<String, String> ru = Map.of();
    private final Map<String, String> is = Map.of();
    private final Map<String, String> el = Map.of();
    private final Map<String, String> es_PR = Map.of();

    @Override
    String getString() {
        return "";
    }
}
