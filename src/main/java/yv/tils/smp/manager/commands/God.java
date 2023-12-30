package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class God implements CommandExecutor {
    public static Map<UUID, Boolean> god = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                selfGod(player);
            } else if (args.length == 1) {
                otherGod(Bukkit.getPlayer(args[0]), sender);
            } else {
                sendUsage(sender);
            }
        } else if (args.length != 1) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ARGUMENT_MISSING));
        } else {
            otherGod(Bukkit.getPlayer(args[0]), sender);
        }
        return true;
    }

    public void selfGod(Player player) {
        UUID uuid = player.getUniqueId();
        if (!god.containsKey(uuid)) {
            god.put(uuid, true);
            new Fly().selfFly(player);
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_ENABLE));
        } else {
            god.remove(uuid);
            new Fly().selfFly(player);
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_DISABLE));
        }
    }

    public void otherGod(Player player, CommandSender sender) {
        if (player == null) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_NOT_ONLINE));
            return;
        }

        UUID uuid = player.getUniqueId();
        if (!god.containsKey(uuid)) {
            god.put(uuid, true);
            new Fly().otherFly(player, sender);
            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_ENABLE_OTHER), List.of("PLAYER"), List.of(player.getName())));
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_ENABLE));
        } else {
            god.remove(uuid);
            new Fly().otherFly(player, sender);
            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_DISABLE_OTHER), List.of("PLAYER"), List.of(player.getName())));
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GODMODE_COMMAND_DISABLE));
        }
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE + "/god [player]");
    }

    public void onDamage(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) e.getEntity();
            UUID uuid = player.getUniqueId();

            if (god.containsKey(uuid)) {
                e.setCancelled(true);
            }
        }
    }
}