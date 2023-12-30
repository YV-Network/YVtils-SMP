package yv.tils.smp.mods.sit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.YVtils;

/**
 * @version 4.6.8
 * @since 4.6.7
 */
public class SitCommand implements CommandExecutor {
    public static final SitManager sitManager = YVtils.sitManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (sitManager.isSitting(player)) {
                sitManager.sitGetter(player);
            } else {
                sitManager.sit(player);
            }
        }
        return false;
    }
}
