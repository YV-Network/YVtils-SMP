package yv.tils.smp.updateutils;

import org.bukkit.Bukkit;
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.SMPPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @since 4.6.6
 * @version 4.6.7
 */
public class UpdateChecker {

    private final SMPPlugin plugin;
    private final int resourceId;

    public UpdateChecker(SMPPlugin plugin, int resourceId) {
        this.plugin  = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info(LanguageFile.DirectFormatter("Update checker is broken, can't find an update! ", "Update checker funktioniert nicht, es konnte kein Update gefunden werden! ") + exception.getMessage());
            }
        });
    }
}