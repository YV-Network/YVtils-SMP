package yv.tils.smp.Placeholder;

import org.bukkit.ChatColor;
import yv.tils.smp.SMPPlugin;

public class LanguagePlaceholder {

    public static String StartMessage() {
        String s;
        String en = MessagePlaceholder.PREFIXENABLE + ChatColor.GREEN + " Plugin is starting!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXENABLE + ChatColor.GREEN + " Plugin startet!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String StartCompletedMessage() {
        String s;
        String en = MessagePlaceholder.PREFIXENABLE + ChatColor.DARK_GREEN + " Plugin start is completed!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXENABLE + ChatColor.DARK_GREEN + " Plugin Start ist abgeschlossen!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String StopMessage() {
        String s;
        String en =     MessagePlaceholder.PREFIXDISABLE + ChatColor.RED + " Plugin is stopping!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s =     MessagePlaceholder.PREFIXDISABLE + ChatColor.RED + " Plugin wird gestoppt!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String StopCompletedMessage() {
        String s;
        String en = MessagePlaceholder.PREFIXDISABLE + ChatColor.DARK_RED + " Plugin stop is completed!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXDISABLE + ChatColor.DARK_RED + " Plugin wurde gestoppt!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String UpToDate() {
        String s;
        String en = MessagePlaceholder.PREFIXNOUPDATE + ChatColor.WHITE + " Plugin is up to date.";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXNOUPDATE + ChatColor.WHITE + " Plugin hat keine Updates zu verfügung!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String UpdateAviaible() {
        String s;
        String en = MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " The Plugin has a new Version available. Load it here: " + ChatColor.GRAY + "https://www.spigotmc.org/resources/yvtils-ba.97642/";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " Plugin hat eine neue Version zum Download verfügbar. Lade es hier herunter: " + ChatColor.GRAY + "https://www.spigotmc.org/resources/yvtils-ba.97642/";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String UnknownTimeFormat() {
        String s;
        String en = "This time Format is not a Option to use!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Dieses Zeit Format ist es nicht zur Auswahl!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String PlayerNotOnline() {
        String s;
        String en = "This player is not online!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Dieser Spieler ist nicht online!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String AlreadyBanned() {
        String s;
        String en = "This player is already banned!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Dieser Spieler ist bereits gebannt!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String LegalTool() {
        String s;
        String en = ChatColor.RED + "This is a legal Item!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.RED + "Das ist ein legales Item!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BetterProt() {
        String s;
        String en = ChatColor.GREEN.toString() + ChatColor.BOLD + "Better Prot";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.GREEN.toString() + ChatColor.BOLD + "Verbesserte Protection";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String Effi10Tool() {
        String s;
        String en = ChatColor.GREEN.toString() + ChatColor.BOLD + "Efficiency 10 Tool";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.GREEN.toString() + ChatColor.BOLD + "Effizienz 10 Werkzeug";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String LightBlock() {
        String s;
        String en = ChatColor.GREEN.toString() + ChatColor.BOLD + "This gives a invisible Light Source (Light Level 15)";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.GREEN.toString() + ChatColor.BOLD + "Lässt dich eine unsichtbare Licht Quelle erschaffen (Licht Level 15)";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DebugStick() {
        String s;
        String en = ChatColor.GREEN.toString() + ChatColor.BOLD + "Let you edit Blocks (You need a Permission from Minecraft Side)";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.GREEN.toString() + ChatColor.BOLD + "Lässt dich Blöcke bearbeiten (Berechtigung wird benötigt, welche von Minecraft seits kommt.)";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String PermissionError() {
        String s;
        String en = "§cMissing Permission: ";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "§cFehlende Berechtigung: ";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String StartUpMessage() {
        String s;
        String en = "";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String Boost1() {
        String s;
        String en = "Press ";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Drücke ";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String Boost2() {
        String s;
        String en = " to boost you!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = " um dich zu boosten!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String ConfigCreateBotToken() {
        String s;
        String en = "YOUR TOKEN HERE";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "DEINEN BOT TOKEN";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String ConfigCreateMissingLanguage() {
        String s;
        String en = "This language is not available in the Moment! Help to translate: " + "https://discord.com/invite/y6uJYzdHc5";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Diese Sprache ist im Moment nicht verfügbar! Helfe zu Übersetzen: " + "https://discord.com/invite/y6uJYzdHc5";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishOff() {
        String s;
        String en = MessagePlaceholder.PREFIXSMP + " §7Vanish is now deactivated!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXSMP + " §7Vanish ist nun deaktiviert!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishOn() {
        String s;
        String en = MessagePlaceholder.PREFIXSMP + " §7Vanish is now activated!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXSMP + " §7Vanish ist nun aktiviert!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishTiPu() {
        String s;
        String en = "You are not vanished! Please use /vanish before using /v itempickup!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du bist nicht im Vanish! Bitte benutze erst /vanish bevor du /v itempickup benutzt!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishTiPuOff() {
        String s;
        String en = MessagePlaceholder.PREFIXSMP + " §7Item Pick Up is now deactivated!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXSMP + " §7Item Pick Up ist nun deaktiviert!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishTiPuOn() {
        String s;
        String en = MessagePlaceholder.PREFIXSMP + " §7Item Pick Up is now activated!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = MessagePlaceholder.PREFIXSMP + " §7Item Pick Up ist nun aktiviert!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishAllreadyOn() {
        String s;
        String en = "You are alredy vanished!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du bist bereits im Vanish!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String VanishAlreadyOff() {
        String s;
        String en = "You are not vanished!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du bist nicht im Vanish!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String CommandUsage() {
        String s;
        String en = ChatColor.GRAY + "Usage" + ChatColor.DARK_GRAY + ": ";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = ChatColor.GRAY + "Benutze" + ChatColor.DARK_GRAY + ": ";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String Replace1() {
        String s;
        String en = "§7Please use\n";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "§7Bitte benutze\n";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String Replace2() {
        String s;
        String en = "\n§7or";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "\n§7oder";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String TabCompleterModCommandDuration() {
        String s;
        String en = "duration";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Dauer";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String TabCompleterModCommandReason() {
        String s;
        String en = "[Reason]";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "[Grund]";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }
}
