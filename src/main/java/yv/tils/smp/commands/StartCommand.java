package yv.tils.smp.commands;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class StartCommand implements CommandExecutor {


    File file = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "Language.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (modifyFile.getBoolean("Started")) {
            String string = modifyFile1.getString("Already_Started_Message");
                String colorcode = string.replace("&0", "§0");
                String colorcode1 = colorcode.replace("&1", "§1");
                String colorcode2 = colorcode1.replace("&2", "§2");
                String colorcode3 = colorcode2.replace("&3", "§3");
                String colorcode4 = colorcode3.replace("&4", "§4");
                String colorcode5 = colorcode4.replace("&5", "§5");
                String colorcode6 = colorcode5.replace("&6", "§6");
                String colorcode7 = colorcode6.replace("&7", "§7");
                String colorcode8 = colorcode7.replace("&8", "§8");
                String colorcode9 = colorcode8.replace("&9", "§9");
                String colorcode10 = colorcode9.replace("&a", "§a");
                String colorcode11 = colorcode10.replace("&b", "§b");
                String colorcode12 = colorcode11.replace("&c", "§c");
                String colorcode13 = colorcode12.replace("&d", "§d");
                String colorcode14 = colorcode13.replace("&e", "§e");
                String colorcode15 = colorcode14.replace("&f", "§f");
                String colorcode16 = colorcode15.replace("&k", "§k");
                String colorcode17 = colorcode16.replace("&l", "§l");
                String colorcode18 = colorcode17.replace("&m", "§m");
                String colorcode19 = colorcode18.replace("&n", "§n");
                String colorcode20 = colorcode19.replace("&o", "§o");
                String colorcode21 = colorcode20.replace("&r", "§r");
                string = colorcode21;


                sender.sendMessage(string);
        }else {
            modifyFile.set("Started", true);
            try {
                modifyFile.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                modifyFile.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("yvtils.smp.play")) {
                    World world = Bukkit.getWorld("world");
                    WorldBorder worldBorder = world.getWorldBorder();
                    Bukkit.setWhitelist(SMPPlugin.getInstance().getConfig().getBoolean("WhitelistafterStart"));
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
                    player.sendTitle(SMPPlugin.getInstance().getConfig().getString("StartTitle.Oben"), SMPPlugin.getInstance().getConfig().getString("StartTitle.Unten"), 20, 50, 20);
                    worldBorder.setSize(SMPPlugin.getInstance().getConfig().getInt("worldborderafterstart"), SMPPlugin.getInstance().getConfig().getInt("worldbordergrowtime"));
                    player.sendMessage(MessagePlaceholder.PREFIXSTART + " " + SMPPlugin.getInstance().getConfig().getString("StartBroadcastMessage"));
                    Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXSTART + " " + SMPPlugin.getInstance().getConfig().getString("StartBroadcastMessage"));
                } else {
                    player.kickPlayer(SMPPlugin.getInstance().getConfig().getString("NotAllowedtoPlayMessage"));
                }
            }
        }
        return true;
    }
}