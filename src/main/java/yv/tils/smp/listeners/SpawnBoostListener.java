package yv.tils.smp.listeners;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * This Code is inspired by this Tutorial '<a href="https://www.youtube.com/watch?v=S9f_mFiYT50">https://www.youtube.com/watch?v=S9f_mFiYT50</a>' from Coole Pizza
 *
 * @since 4.6.6
 * @version 4.6.7
 *
 */

public class SpawnBoostListener implements Listener {

    private final int multiplyValue;
    private final int spawnRadius;
    private final List<Player> flying = new ArrayList<>();
    private final List<Player> boosted = new ArrayList<>();

    public SpawnBoostListener(Plugin plugin) {
        this.spawnRadius = plugin.getConfig().getInt("spawnradius");
        this.multiplyValue = plugin.getConfig().getInt("multiplyValue");

        Bukkit.getScheduler().runTaskTimer(plugin, () -> Bukkit.getWorld("world").getPlayers().forEach(player -> {
            if (SMPPlugin.getInstance().fly.contains(player.getUniqueId()) || SMPPlugin.getInstance().godmode.contains(player.getUniqueId())) return;
            if (player.getGameMode() != GameMode.SURVIVAL) return;
            player.setAllowFlight(isInSpawnRadius(player));
            if (flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.setGliding(false);
                boosted.remove(player);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    flying.remove(player);
                }, 5);
            }
        }), 0, 3);
    }

    @EventHandler
    public void onTest(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if (SMPPlugin.getInstance().fly.contains(event.getPlayer().getUniqueId()) || SMPPlugin.getInstance().godmode.contains(event.getPlayer().getUniqueId())) return;
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setGliding(false);
        boosted.remove(player);
        flying.remove(player);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        if (SMPPlugin.getInstance().fly.contains(event.getPlayer().getUniqueId()) || SMPPlugin.getInstance().godmode.contains(event.getPlayer().getUniqueId())) return;
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if (!isInSpawnRadius(event.getPlayer())) {
            if (!event.getPlayer().getWorld().equals("world")) {
                event.getPlayer().setAllowFlight(false);
            }
            return;
        }
        BaseComponent keytopress = new KeybindComponent("key.swapOffhand");
        event.setCancelled(true);
        event.getPlayer().setGliding(true);
        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, (new ComponentBuilder(LanguageFile.getMessage(LanguageMessage.SPAWN_ELYTRA_BOOST) + " ")).append(keytopress).color(ChatColor.DARK_GRAY).create());
        flying.add(event.getPlayer());
    }

    @EventHandler
    public void onLandDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER
        && (event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)
        && flying.contains(event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void onHandSwap(PlayerSwapHandItemsEvent event) {
        if (flying.contains(event.getPlayer())) {
        if (boosted.contains(event.getPlayer())) return;
        event.setCancelled(true);
        boosted.add(event.getPlayer());

        event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(multiplyValue));
    }}

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if (event.getEntityType() == EntityType.PLAYER && flying.contains(event.getEntity())) event.setCancelled(true);
    }

    private boolean isInSpawnRadius(Player player) {
        if (!player.getWorld().getName().equals("world")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }}