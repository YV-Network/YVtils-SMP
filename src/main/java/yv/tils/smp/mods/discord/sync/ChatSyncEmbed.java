package yv.tils.smp.mods.discord.sync;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.status.StatusConfigManager;

import java.awt.*;

/**
 * @version CH2-1.0.0
 * @since 4.6.8
 */
public class ChatSyncEmbed {
    EmbedBuilder builder = new EmbedBuilder();

    YamlConfiguration yml = new StatusConfigManager().SavedRequest();

    private Color color(Player sender) {

        if (!yml.contains(sender.getUniqueId().toString())) return new Color(0xABFF99);

        String statuscolor = (String) yml.get(sender.getUniqueId().toString());

        statuscolor = HexSupport.hex(statuscolor);

        switch (ChatColor.getLastColors(statuscolor)) {
            case "§0" -> {
                return new Color(0x000000);
            }
            case "§1" -> {
                return new Color(0x0000AA);
            }
            case "§2" -> {
                return new Color(0x00AA00);
            }
            case "§3" -> {
                return new Color(0x00AAAA);
            }
            case "§4" -> {
                return new Color(0xAA0000);
            }
            case "§5" -> {
                return new Color(0xAA00AA);
            }
            case "§6" -> {
                return new Color(0xFFAA00);
            }
            case "§7" -> {
                return new Color(0xAAAAAA);
            }
            case "§8" -> {
                return new Color(0x555555);
            }
            case "§9" -> {
                return new Color(0x5555FF);
            }
            case "§a" -> {
                return new Color(0x55FF55);
            }
            case "§b" -> {
                return new Color(0x55FFFF);
            }
            case "§c" -> {
                return new Color(0xFF5555);
            }
            case "§d" -> {
                return new Color(0xFF55FF);
            }
            case "§e" -> {
                return new Color(0xFFFF55);
            }
            case "§f" -> {
                return new Color(0xFFFFFF);
            }
            default -> {
                return new Color(0xABFF99);
            }
        }

    }

    public EmbedBuilder Embed(Player sender, String message) {
        return builder
                .setAuthor(sender.getName(), null, "https://cravatar.eu/head/" + sender.getName() + "/600")
                .setDescription(message)
                .setColor(color(sender));
    }
}