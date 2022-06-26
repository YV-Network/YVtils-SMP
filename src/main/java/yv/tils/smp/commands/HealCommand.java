package yv.tils.smp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.HEAL_PLAYER_HEALED));
        }
        return false;
    }
}
