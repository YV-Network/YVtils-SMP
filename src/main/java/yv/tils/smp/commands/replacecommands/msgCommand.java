package yv.tils.smp.commands.replacecommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import yv.tils.smp.Placeholder.MessagePlaceholder;

public class msgCommand implements Listener {

    @EventHandler
    public void onPlayerMSGCommandPreProcess(PlayerCommandPreprocessEvent event) {

        final Player player = event.getPlayer();
        final String cmd = event.getMessage();
        final String[] args = cmd.split(" ");

        if (cmd.startsWith("/msg") || cmd.equals("/w") || cmd.equals("/tell")) {
            event.setCancelled(true);
                if (player.hasPermission("yv.tils.smp.command.directmessage")) {
                    TextComponent c = new TextComponent("§7Please use\n");
                    TextComponent click = new TextComponent("§e/dm");


                    click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/dm"));
                    click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                    c.addExtra(click);
                    player.spigot().sendMessage(c);

                } else {
                    player.sendMessage(MessagePlaceholder.PERMISSIONERROR + ": yv.tils.smp.command.directmessage");
                }}}}
