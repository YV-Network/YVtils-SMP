package de.yvtils.ba.commands.replacecommands;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class seedcommand implements Listener {

    @EventHandler
    public void onPlayerSeedCommandPreProcess(PlayerCommandPreprocessEvent event) {

        final Player player = event.getPlayer();
        final String cmd = event.getMessage();
        final String[] args = cmd.split(" ");

        if (cmd.startsWith("/seed")) {
            event.setCancelled(true);
            if (args.length == 1) {
                if (player.hasPermission("yvtils.ba.command.notreplace.seed")) {
                    TextComponent c = new TextComponent("§7Please use\n");
                    TextComponent click = new TextComponent("§e/seed showyouonlyformebecauseineedtoknowtheseed");
                    TextComponent click1 = new TextComponent("\n§7or");
                    TextComponent click2 = new TextComponent("\n§e/seed show");

                    click2.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/seed show"));
                    click2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                    click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/seed showyouonlyformebecauseineedtoknowtheseed"));
                    click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                    c.addExtra(click);
                    c.addExtra(click1);
                    c.addExtra(click2);
                    player.spigot().sendMessage(c);

                } else {
                    player.sendMessage(Main.getInstance().getConfig().getString("NotPermissionforSeedMessage") + ": yvtils.ba.command.notreplace.seed");
                }
            } else if (args.length == 2) {
                switch (args[1].toLowerCase()) {
                    case "showyouonlyformebecauseineedtoknowtheseed", "show" -> {

                        TextComponent c = new TextComponent(MessagePlaceholder.PREFIXSEED);
                        TextComponent click = new TextComponent(" §7[" + "§a" + Bukkit.getWorld("world").getSeed() + "§7]");

                        click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.valueOf(Bukkit.getWorld("world").getSeed())));
                        click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                        c.addExtra(click);
                        player.spigot().sendMessage(c);
                    }}}}}}