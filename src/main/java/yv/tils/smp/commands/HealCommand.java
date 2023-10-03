package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.8.1
 */
public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
                player.sendMessage(LanguageFile.getMessage(LanguageMessage.HEAL_PLAYER_HEALED));
            }else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
                player.sendMessage(LanguageFile.getMessage(LanguageMessage.HEAL_PLAYER_HEALED));

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PLAYER");
                list2.add(player.getName());

                sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.HEAL_OTHER_PLAYER_HEALED), list1, list2));
            } else {
                sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " /heal [player]");
            }
        }
        return false;
    }
}