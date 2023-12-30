package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.YVtils;
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
public class Fly implements CommandExecutor, Listener {
    public static Map<UUID, Boolean> fly = new HashMap<>();
    public static Map<UUID, Boolean> airAfter = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                selfFly(player);
            } else if (args.length == 1) {
                otherFly(Bukkit.getPlayer(args[0]), sender);
            } else {
                sendUsage(sender);
            }
        } else if (args.length != 1) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ARGUMENT_MISSING));
        } else {
            otherFly(Bukkit.getPlayer(args[0]), sender);
        }
        return true;
    }

    public void selfFly(Player player) {
        UUID uuid = player.getUniqueId();
        if (!fly.containsKey(uuid)) {
            fly.put(uuid, true);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_ENABLE));
        } else {
            if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) {
                fly.remove(uuid);
                player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
                return;
            }
            if (player.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.DOWN).getType().isAir()) {
                airAfter.put(uuid, true);
            }
            fly.remove(uuid);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
        }
    }

    public void otherFly(Player player, CommandSender sender) {
        if (player == null) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_NOT_ONLINE));
            return;
        }

        UUID uuid = player.getUniqueId();
        if (!fly.containsKey(uuid)) {
            fly.put(uuid, true);
            player.setAllowFlight(true);
            player.setFlying(true);
            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_ENABLE_OTHER), List.of("PLAYER"), List.of(player.getName())));
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_ENABLE));
        } else {
            if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) {
                fly.remove(uuid);
                sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE_OTHER), List.of("PLAYER"), List.of(player.getName())));
                player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
                return;
            }
            if (player.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.DOWN).getType().isAir()) {
                airAfter.put(uuid, true);
            }
            fly.remove(uuid);
            player.setAllowFlight(false);
            player.setFlying(false);
            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE_OTHER), List.of("PLAYER"), List.of(player.getName())));
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
        }
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE + "/fly [player]");
    }

    public void onLandingDamage(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) e.getEntity();
            UUID uuid = player.getUniqueId();
            if (fly.containsKey(uuid)) {
                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    e.setCancelled(true);
                }
            } else if (airAfter.containsKey(uuid)) {
                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    e.setCancelled(true);
                    airAfter.remove(uuid);
                }
            }
        }
    }

    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (fly.containsKey(uuid)) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

    public void onRejoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (fly.containsKey(uuid)) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

    public void onGamemodeSwitch(PlayerGameModeChangeEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        Bukkit.getScheduler().runTaskLater(YVtils.getInstance(), () -> {
            if (fly.containsKey(uuid)) {
                player.setAllowFlight(true);
                player.setFlying(true);
            }
        }, 1);

    }
}