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

    public static String NoBotTokenGiven() {
        String s;
        String en = "No Bot Token was given! Deactivate the Discord Bot or insert a valid Token!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Kein Bot Token wurde erkannt! Deaktiviere den Bot oder trage einen Token ein.";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BotStartupFailed() {
        String s;
        String en = "Bot Startup Failed! Please check your Config of the Bot";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Bot Start ist fehlgeschlagen! Bitte überprüfe deine Konfigurationen";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BotStartupFinished() {
        String s;
        String en = "Bot Startup Finished! Bot should be Online now!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Bot Start war erfolgreich! Der Bot sollte nun Online sein!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BotActivity() {
        //Activity -> Streaming; Watching; Playing; Competing; None;
        String s;
        String en = "You can use Streaming; Watching; Playing; Competing; None";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du kannst Streaming; Watching; Playing; Competing; None benutzen";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BotActivityStreamingUrl() {
        String s;
        String en = "You only need a Url, when you use the Activity Streaming! Otherwise you don't need one";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du brauchst nur eine Url, wenn du die Aktivität Streaming ausgewählt hast! Ansonsten benötigst du keine";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String BotStatus() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        String s;
        String en = "You can use Online; Idle; DND; Offline; Invisible";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Du kannst Online; Idle; DND; Offline; Invisible benutzen";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String ChannelID() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        String s;
        String en = "Here you can set the Channel for the whitelist Feature! Players can write their MC Name in there and they will get whitelisted! Copy the Channel ID and paste it here";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Hier kannst du einen Kannal für das Whitelist Modul auswählen! Spieler können dann ihren MC Namen dort hineninschreiben und werden automatisch gewhitelistet! Kopiere die Kannal ID und füge sie hier ein";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String ConfigCreateChannelID() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        String s;
        String en = "CHANNEL ID HERE";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "KANAL ID HIER";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DCConsoleLog_NameChangeEvent(String oldname, String newname, String discorduser) {
        String s;
        String en = "Discord User '" + discorduser + "' changed their whitelisted Minecraft Account from '" + oldname + "' to '" + newname + "'";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Discord User '" + discorduser + "' hat seinen gewhitelisteten Account von '" + oldname + "' zu '" + newname + "' geändert";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DCConsoleLog_NameAddEvent(String name, String discorduser) {
        String s;
        String en = "Discord User '" + discorduser + "' has whitelisted their Minecraft Account '" + name + "'";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Discord User '" + discorduser + "' hat seinen Minecraft Account '" + name + "' gewhitelistet";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DCConsoleLog_NameWrongEvent(String name, String discorduser) {
        String s;
        String en = "Discord User '" + discorduser + "' has tried to whitelisted their Minecraft Account '" + name + "', but it failed! -> Account doesn't exist";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Discord User '" + discorduser + "' hat versucht seinen Minecraft Account '" + name + "' zu whitelisten, aber es ist fehlgeschlagen! -> Account existiert nicht";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DCConsoleLog_NameCheckServerError(String name, String discorduser) {
        String s;
        String en = "Discord User '" + discorduser + "' has tried to whitelisted their Minecraft Account '" + name + "', but it failed! -> Server for checking Names isn't available";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Discord User '" + discorduser + "' hat versucht seinen Minecraft Account '" + name + "' zu whitelisten, aber es ist fehlgeschlagen! -> Server fürs checken der Namen ist nicht erreichbar";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }

    public static String DCEmbedAuthorIcon() {
        String s;
        String en = "You can insert here a URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) for a Icon in the Embeds!";
        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            s = "Hier kannst du eine URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) einfügen, damit die Embeds ein Bild haben!";
        }else {
            s = "Language is not available! English Message -> " + en;
        }
        return s;
    }
}