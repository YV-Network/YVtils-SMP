package yv.tils.smp.mods.admin.moderation;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import yv.tils.smp.utils.MojangAPI;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class altCommands implements Listener {
    public void onPlayerBanCommandPreProcess(PlayerCommandPreprocessEvent e) {
        final Player player = e.getPlayer();
        final String cmd = e.getMessage();
        final String[] args = cmd.split(" ");

        String cmdlowercase = args[0].toLowerCase();

        if (!cmdlowercase.equals("/ban") && !cmdlowercase.equals("/kick") && !cmdlowercase.equals("/pardon")) return;

        Player target = Bukkit.getServer().getPlayer(args[1]);
        OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(MojangAPI.Name2UUID(args[1]));

        switch (cmdlowercase) {
            case "/kick" -> {
                String reason;

                if (args.length == 2) {
                    reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                } else {
                    reason = new Moderation().getReason(args, 2);
                }

                new Moderation().playerKick(player, target, reason);
            }
            case "/ban" -> {
                String reason;

                if (args.length == 2) {
                    reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                } else {
                    reason = new Moderation().getReason(args, 2);
                }

                if (target == null) {
                    new Moderation().playerBan(player, offlineTarget, reason);
                    return;
                }

                new Moderation().playerBan(player, target, reason);
            }
            case "/pardon" -> {
                new Moderation().playerUnban(player, offlineTarget);
            }
        }
        e.setCancelled(true);
    }
}