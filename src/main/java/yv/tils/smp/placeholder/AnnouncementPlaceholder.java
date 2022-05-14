package yv.tils.smp.placeholder;

import yv.tils.smp.LanguageSystem.LanguagePlaceholder;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class AnnouncementPlaceholder {
    public static String CONFIGVERSION = "10";
    public static String STARTUPANNOUNCE() {

        String Announcement = null;
        if (!LanguagePlaceholder.StartMessage().isEmpty()) {
            Announcement = LanguagePlaceholder.StartUpMessage();
        }
        return Announcement;
    }
}
