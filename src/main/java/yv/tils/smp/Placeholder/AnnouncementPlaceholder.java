package yv.tils.smp.Placeholder;

import yv.tils.smp.SMPPlugin;

public class AnnouncementPlaceholder {
    public static String CONFIGVERSION = "9";
    public static String STARTUPANNOUNCE() {

        String Announcement = null;
        if (LanguagePlaceholder.StartMessage().isEmpty()) {
        } else {
            Announcement = LanguagePlaceholder.StartUpMessage();

        }
        return Announcement;
    }
}
