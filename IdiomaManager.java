import java.util.Locale;
import java.util.ResourceBundle;

public class IdiomaManager {
    private ResourceBundle messages;

    public void setLocale(String language, String country) {
        @SuppressWarnings("deprecation")
        Locale locale = new Locale(language, country);
        messages = ResourceBundle.getBundle("PACK_IDIOMAS.msg", locale);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }
}
