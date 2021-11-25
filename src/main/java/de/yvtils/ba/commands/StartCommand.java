package de.yvtils.ba.commands;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                //if (Objects.equals(Main.getInstance().getConfig().getString("Started"), "false")) {
                    //Main.getInstance().getConfig().set("Started", true);
                    //Main.getInstance().saveConfig();
                    if (player.hasPermission("yvtils.ba.play")) {
                        World world = Bukkit.getWorld("world");
                        WorldBorder worldBorder = world.getWorldBorder();
                        Bukkit.setWhitelist(Main.getInstance().getConfig().getBoolean("WhitelistafterStart"));
                        player.resetPlayerTime();
                        player.resetPlayerWeather();
                        player.getInventory().clear();
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.setLevel(0);
                        player.setExp(0);
                        worldBorder.setWarningDistance(0);
                        worldBorder.setDamageAmount(2);
                        worldBorder.setDamageBuffer(5);
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendTitle(Main.getInstance().getConfig().getString("StartTitle.Oben"), Main.getInstance().getConfig().getString("StartTitle.Unten"), 20, 50, 20);
                        worldBorder.setSize(Main.getInstance().getConfig().getInt("worldborderafterstart"), Main.getInstance().getConfig().getInt("worldbordergrowtime"));
                        player.sendMessage(MessagePlaceholder.PREFIXSTART + " " + Main.getInstance().getConfig().getString("StartBroadcastMessage"));
                        Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXSTART + " " + Main.getInstance().getConfig().getString("StartBroadcastMessage"));
                    } else {
                        player.kickPlayer(Main.getInstance().getConfig().getString("NotAllowedtoPlayMessage"));
                    }
                //} else {
                 //   sender.sendMessage(MessagePlaceholder.PREFIXSTART + " " + Main.getInstance().getConfig().getString("AlreadyStartedMessage"));
                //}
                //Bukkit.getConsoleSender().sendMessage(String.valueOf(Main.getInstance().getConfig().getBoolean("Started")));
            }
            return true;
        }
    }