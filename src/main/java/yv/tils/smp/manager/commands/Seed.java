package yv.tils.smp.manager.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Seed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("COMMAND");
        list2.add("§e/seed show");

            if (args.length == 1) {
                if (player.hasPermission("yvtils.smp.command.bypass.seed")) {
                    TextComponent c = new TextComponent(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_NEW_COMMAND_INFO), list1, list2));
                    c.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/seed show"));
                    c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_COPY_COMMAND_TO_CLIPBOARD))));
                    player.spigot().sendMessage(c);
                } else {
                    player.sendMessage(LanguageFile.getMessage(LanguageMessage.MISSING_PERMISSION) + " yvtils.smp.command.bypass.seed");
                }
            } else if (args.length == 2) {
                if (args[1].toLowerCase().equals("show")) {
                    if (player.hasPermission("yvtils.smp.command.bypass.seed")) {
                        TextComponent c = new TextComponent(Prefix.PREFIXSEED);
                        TextComponent click = new TextComponent(" §7[" + "§a" + Bukkit.getWorld("world").getSeed() + "§7]");
                        click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, String.valueOf(Bukkit.getWorld("world").getSeed())));
                        click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(LanguageFile.getMessage(LanguageMessage.COMMAND_REPLACE_COPY_COMMAND_TO_CLIPBOARD))));
                        c.addExtra(click);
                        player.spigot().sendMessage(c);
                    } else {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.MISSING_PERMISSION) + " yvtils.smp.command.bypass.seed");
                    }
                } else {
                    if (player.hasPermission("yvtils.smp.command.bypass.seed")) {
                        TextComponent c = new TextComponent(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE));
                        TextComponent click = new TextComponent(" §e/seed show");

                        click.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, "/seed show"));
                        click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§7Click to copy to Clipboard")));

                        c.addExtra(click);
                        player.spigot().sendMessage(c);
                    } else {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.MISSING_PERMISSION) + " yvtils.smp.command.bypass.seed");
                    }
                }
            }
        return false;
    }
}