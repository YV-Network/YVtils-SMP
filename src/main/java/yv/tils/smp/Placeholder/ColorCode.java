package yv.tils.smp.placeholder;

import org.bukkit.ChatColor;

/**
 * @since 4.6.6
 * @version 4.6.8
 */
public class ColorCode {
    public String ColorCodes(String args) {
        return ChatColor.translateAlternateColorCodes('&', args);
    }
}
