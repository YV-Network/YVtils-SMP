package yv.tils.smp.mods.other;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.manager.commands.Fly;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * This Code is inspired by this Tutorial '<a href="https://www.youtube.com/watch?v=S9f_mFiYT50">https://www.youtube.com/watch?v=S9f_mFiYT50</a>' from Coole Pizza
 *
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class SpawnElytra implements Listener {

    private static SpawnElytra instance;
    private static int multiplyValue;
    private static int spawnRadius;
    private final List<Player> flying = new ArrayList<>();
    private final List<Player> boosted = new ArrayList<>();
    YVtils main = YVtils.getInstance();

    public SpawnElytra() {

        instance = this;

        spawnRadius = main.getConfig().getInt("spawnradius");
        multiplyValue = main.getConfig().getInt("multiplyValue");

        Bukkit.getScheduler().runTaskTimer(main, () -> Bukkit.getWorld("world").getPlayers().forEach(player -> {
            if (Fly.fly.containsKey(player.getUniqueId())) return;
            if (player.getGameMode() != GameMode.SURVIVAL) return;
            player.setAllowFlight(isInSpawnRadius(player));
            if (flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.setGliding(false);
                boosted.remove(player);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    flying.remove(player);
                }, 5);
            }
        }), 0, 3);
    }

    public static SpawnElytra getInstance() {
        return instance;
    }

    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();

        if (Fly.fly.containsKey(e.getPlayer().getUniqueId())) return;
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGliding(false);
        boosted.remove(player);
        flying.remove(player);
    }

    public void onDoubleJump(PlayerToggleFlightEvent e) {
        if (Fly.fly.containsKey(e.getPlayer().getUniqueId())) return;
        if (e.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if (!isInSpawnRadius(e.getPlayer())) {
            if (!e.getPlayer().getWorld().equals("world")) {
                e.getPlayer().setAllowFlight(false);
            }
            return;
        }
        BaseComponent keyPress = new KeybindComponent("key.swapOffhand");
        e.setCancelled(true);
        e.getPlayer().setGliding(true);
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder(LanguageFile.getMessage(LanguageMessage.SPAWN_ELYTRA_BOOST) + " ")).append(keyPress).color(ChatColor.DARK_GRAY).create());
        flying.add(e.getPlayer());
    }

    public void onLandDamage(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER
                && (e.getCause() == EntityDamageEvent.DamageCause.FALL || e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)
                && flying.contains(e.getEntity())) e.setCancelled(true);
    }

    public void onHandSwap(PlayerSwapHandItemsEvent e) {
        if (flying.contains(e.getPlayer())) {
            if (boosted.contains(e.getPlayer())) return;
            e.setCancelled(true);
            boosted.add(e.getPlayer());

            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(multiplyValue));
        }
    }

    public void onToggleGlide(EntityToggleGlideEvent e) {
        if (e.getEntityType() == EntityType.PLAYER && flying.contains(e.getEntity())) e.setCancelled(true);
    }

    private boolean isInSpawnRadius(Player player) {
        if (!player.getWorld().getName().equals("world")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }
}
