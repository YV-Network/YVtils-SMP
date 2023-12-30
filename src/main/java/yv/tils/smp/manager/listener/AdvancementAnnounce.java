package yv.tils.smp.manager.listener;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import yv.tils.smp.mods.admin.vanish.Vanish;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class AdvancementAnnounce implements Listener {
    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent e) {

        if (e.getAdvancement().getKey().getKey().contains("recipes")) return;

        if (Vanish.vanish.containsKey(e.getPlayer().getUniqueId()) && Vanish.vanish.get(e.getPlayer().getUniqueId())) return;

        String message = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.ADVANCEMENT_ANNOUNCEMENT), List.of("PLAYER"), List.of(e.getPlayer().getName()));

        String[] messageSplit = message.split(" ");

        List<TextComponent> components = new ArrayList<>();

        for (String s : messageSplit) {
            TextComponent c;
            if (s.startsWith("ADVANCEMENT") || s.endsWith("ADVANCEMENT")) {
                c = new TextComponent(e.getAdvancement().getDisplay().getType().getColor() + "[" + e.getAdvancement().getDisplay().getTitle() + "]");
                c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        TextComponent.fromLegacyText(
                                e.getAdvancement().getDisplay().getType().getColor() +
                                        e.getAdvancement().getDisplay().getTitle() + "\n" +
                                        e.getAdvancement().getDisplay().getDescription())));
            }else {
                c = new TextComponent(s + " ");
            }
            components.add(c);
        }

        TextComponent m = new TextComponent();
        for (TextComponent c : components) {
            m.addExtra(c);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(m);
        }

    }
}