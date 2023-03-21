package yv.tils.smp.modules.discord.EmbedManager;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.util.Objects;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class EmbedDesign {

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mc_dcbridge = YamlConfiguration.loadConfiguration(file1);
    String st;

    public String EmbedDesign_AUTHORICON() {
        if (!Objects.equals(mc_dcbridge.getString("EmbedSettings.AuthorIconURL"), null)) {
            st = mc_dcbridge.getString("EmbedSettings.AuthorIconURL");
        }
        return st;
    }

    public String EmbedDesign_AUTHOR() {
        if (!Objects.equals(mc_dcbridge.getString("EmbedSettings.Author"), null)) {
            st = mc_dcbridge.getString("EmbedSettings.Author");
        }
        return st;
    }

}
