package languages;

import java.util.Locale;
import java.util.Map;

abstract public class Localizator {
    protected Locale locale = new Locale("ru");

    abstract String getString();

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
