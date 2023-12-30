package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.YVtils;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Start implements CommandExecutor {
    File file = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (ymlFile.getBoolean("Started")) {
            sender.sendMessage(HexSupport.hex(LanguageFile.getMessage(LanguageMessage.SMP_ALREADY_STARTED)));
        } else {
            ymlFile.set("Started", true);
            try {
                ymlFile.save(file);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("yvtils.smp.play")) {
                    World world = Bukkit.getWorld("world");
                    WorldBorder worldBorder = world.getWorldBorder();
                    Bukkit.setWhitelist(YVtils.getInstance().getConfig().getBoolean("WhitelistafterStart"));
                    player.resetPlayerTime();
                    player.resetPlayerWeather();
                    player.getInventory().clear();
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.setLevel(0);
                    player.setExp(0);
                    world.setTime(0);
                    worldBorder.setWarningDistance(0);
                    worldBorder.setDamageAmount(2);
                    worldBorder.setDamageBuffer(5);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendTitle(YVtils.getInstance().getConfig().getString("StartTitle.Top"), YVtils.getInstance().getConfig().getString("StartTitle.Bottom"), 20, 50, 20);
                    worldBorder.setSize(YVtils.getInstance().getConfig().getInt("worldborderafterstart"), TimeUnit.SECONDS, YVtils.getInstance().getConfig().getInt("worldbordergrowtime"));

                    List<String> list1 = new ArrayList<>();
                    List<String> list2 = new ArrayList<>();
                    list1.add("PREFIXSTART");
                    list2.add(Prefix.PREFIXSTART);

                    player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.SMP_START_MESSAGE), list1, list2));
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.SMP_START_MESSAGE), list1, list2));
                } else {
                    player.kickPlayer("");
                }
            }
        }
        return true;
    }
}