package yv.tils.smp.commands.replacecommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import yv.tils.smp.Placeholder.LanguagePlaceholder;
import yv.tils.smp.Placeholder.MessagePlaceholder;

public class BanCommand implements Listener {

    @EventHandler
    public void onPlayerBanCommandPreProcess(PlayerCommandPreprocessEvent event) {

        final Player player = event.getPlayer();
        final String cmd = event.getMessage();
        final String[] args = cmd.split(" ");

        if (cmd.equals("/ban")) {
            event.setCancelled(true);
                if (player.hasPermission("yv.tils.smp.command.moderation.ban")) {
                    TextComponent c = new TextComponent(LanguagePlaceholder.Replace1());
                    TextComponent click = new TextComponent("§e/mod ban");
                    TextComponent click1 = new TextComponent(LanguagePlaceholder.Replace2());
                    TextComponent click2 = new TextComponent("\n§e/mod tempban");

                    click2.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/mod tempban"));
                    click2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                    click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/mod ban"));
                    click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                    c.addExtra(click);
                    c.addExtra(click1);
                    c.addExtra(click2);
                    player.spigot().sendMessage(c);

                } else {
                    player.sendMessage(MessagePlaceholder.PERMISSIONERROR + ": yv.tils.smp.command.moderation.ban");
                }}}}