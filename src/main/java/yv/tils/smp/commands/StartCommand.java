package yv.tils.smp.commands;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.placeholder.MessagePlaceholder;
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

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class StartCommand implements CommandExecutor {

    File file = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (modifyFile.getBoolean("Started")) {
            String s = new ColorCode().ColorCodes("language.getString(AlreadyStarted)");
            sender.sendMessage(s);
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