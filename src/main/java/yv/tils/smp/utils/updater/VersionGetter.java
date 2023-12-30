package yv.tils.smp.utils.updater;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.Log;
import yv.tils.smp.utils.internalapi.StringReplacer;
import yv.tils.smp.utils.internalapi.Variables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class VersionGetter {

    private static String version = "CHx-x.x.x";
    private static String plVersion = "CHx-x.x.x";

    public void onPlayerJoin(Player player) {
        if ((player.isOp() || player.hasPermission("yvtils.smp.update")) && !Objects.equals(plVersion, version)) {
            player.sendMessage(LanguageFile.DirectFormatter(
                    Prefix.PREFIXUPDATE + " §fUpdate Available:\n" +
                            "§eRunning Version: " + plVersion + "\n" +
                            "§aAvailable Version: " + version,
                    Prefix.PREFIXUPDATE + " §fUpdate Verfügbar:\n" +
                            "§eMomentane Version: " + plVersion + "\n" +
                            "§aNeueste Version: " + version));
        }
    }

    public void updateChecker(String ServerPluginVersion) {

        plVersion = ServerPluginVersion;
        webRequest();

        if (!Objects.equals(plVersion, version)) {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXUPDATE");
            list2.add(Prefix.PREFIXUPDATE);
            list1.add("NEWVERSION");
            list2.add(version);
            list1.add("OLDVERSION");
            list2.add(Variables.PluginVersion);
            list1.add("LINK");
            list2.add("https://modrinth.com/plugin/yvtils_smp");

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UPDATE_AVAILABLE), list1, list2));
        } else {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXNOUPDATE");
            list2.add(Prefix.PREFIXNOUPDATE);

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UP_TO_DATE), list1, list2));
        }
    }

    private String webRequest() {
        try {
            String url = "https://yvnetwork.de/yvtils/smp.txt";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();
                connection.disconnect();

                version = content.toString();
            } else {
                Bukkit.getConsoleSender().sendMessage(LanguageFile.DirectFormatter("The Update Checker has an error! Please contact the Support, if you want to fix this.", "Beim checken nach einem Update ist ein Fehler aufgetreten! Bitte kontaktiere den Support!"));
                new Log("Response Code:" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return version;
    }
}