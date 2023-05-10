package yv.tils.smp.commands.replacecommands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class seedcommand implements Listener {

    @EventHandler
    public void onPlayerSeedCommandPreProcess(PlayerCommandPreprocessEvent event) {

        final Player player = event.getPlayer();
        final String cmd = event.getMessage();
        final String[] args = cmd.split(" ");

        String cmdlowercase = cmd.toLowerCase();

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("COMMAND");
        list2.add("§e/seed show");


        if (cmdlowercase.startsWith("/seed")) {
            event.setCancelled(true);
            if (args.length == 1) {
                if (player.hasPermission("yvtils.smp.command.bypass.seed")) {
                    TextComponent c = new TextComponent(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_NEW_COMMAND_INFO), list1, list2));
                    c.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/seed show"));
                    c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_COPY_COMMAND_TO_CLIPBOARD))));
                    player.spigot().sendMessage(c);
                } else {
                    player.sendMessage(MessagePlaceholder.PERMISSIONERROR + " yvtils.smp.command.bypass.seed");
                }
            } else if (args.length == 2) {
                switch (args[1].toLowerCase()) {
                    case "show" -> {
                        if (player.hasPermission("yvtils.smp.command.bypass.seed")) {

                        TextComponent c = new TextComponent(MessagePlaceholder.PREFIXSEED);
                        TextComponent click = new TextComponent(" §7[" + "§a" + Bukkit.getWorld("world").getSeed() + "§7]");
                        click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.valueOf(Bukkit.getWorld("world").getSeed())));
                        click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_COPY_COMMAND_TO_CLIPBOARD))));
                        c.addExtra(click);
                        player.spigot().sendMessage(c);
                    }else {
                            player.sendMessage(MessagePlaceholder.PERMISSIONERROR + " yvtils.smp.command.bypass.seed");
                        }}
                    default -> {
                        if (player.hasPermission("yv.tils.smp.command.bypass.seed")) {
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
                            player.sendMessage(SMPPlugin.getInstance().getConfig().getString("NotPermissionforSeedMessage") + ": yvtils.smp.command.bypass.seed");
                        }
                    }
                }}}}}