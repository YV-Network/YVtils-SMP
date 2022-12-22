package yv.tils.smp.modules.fun.sit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.ServerStart_StopEvent;

public class SitCommand implements CommandExecutor {
    public static final SitManager sitManager = ServerStart_StopEvent.sitManager;
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
