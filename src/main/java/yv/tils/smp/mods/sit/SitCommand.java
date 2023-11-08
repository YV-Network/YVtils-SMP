package yv.tils.smp.mods.sit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.YVtils;

/**
 * @since 4.6.7
 * @version 4.6.8
 */
public class SitCommand implements CommandExecutor {
    public static final SitManager sitManager = YVtils.sitManager;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            if (sitManager.isSitting(player)) {
                sitManager.sitGetter(player);
            }else {
                sitManager.sit(player);
            }
        }
        return false;
    }
}
