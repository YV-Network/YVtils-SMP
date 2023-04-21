package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.smp.modules.discord.EmbedManager.whitelist.Embed.AccountCantExist;
import yv.tils.smp.modules.discord.EmbedManager.whitelist.Embed.discord.Check;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class AccountCheck {

    public EmbedBuilder WhitelistCheck(String mc) {

        OfflinePlayer player = Bukkit.getOfflinePlayer(mc);

        if (!mc.matches("[a-zA-Z0-9_]+")) {
            //Account can't exist
            return new AccountCantExist().Embed(mc);
        }

        String dc = new ImportWhitelist().reader("null", mc, player.getUniqueId().toString()).get(0);

        boolean b = player.isWhitelisted();
        return new Check().Embed(mc,
                dc,
                b);
    }
}