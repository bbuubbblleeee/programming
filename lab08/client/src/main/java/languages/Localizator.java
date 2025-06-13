package languages;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Locale;

abstract public class Localizator {
    protected static Locale locale = new Locale("ru");
    public static ObjectProperty<Locale> localeProperty = new SimpleObjectProperty<>(locale) {
    };

    abstract public String getString(String key);
    abstract public String getStringFormatted(String key, Object[] field);

    public Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale localeNew) {
        locale = localeNew;
        localeProperty.set(locale);
    }
}
