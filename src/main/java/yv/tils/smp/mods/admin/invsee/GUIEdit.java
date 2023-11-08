package yv.tils.smp.mods.admin.invsee;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class GUIEdit implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("PLAYER");
        list2.add("");

        String gui_name = e.getView().getTitle();
        String[] gui_words = gui_name.split(" ");
        if (gui_words.length <= 2) return;
        String gui_name2 = gui_words[0] + " " + gui_words[1] + " ";

        if (!gui_name2.equals(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_INVSEE_INVENTORY),list1 ,list2)) && !e.getView().getTitle().contains(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_INVSEE_ENDERCHEST),list1 ,list2))) return;
        if (e.getWhoClicked().hasPermission("yvtils.smp.invsee.edit")) {
            e.getWhoClicked().sendMessage("I'm sorry, this Feature has not been implemented yet.");
            e.setCancelled(true);
        }else {
            e.setCancelled(true);
        }
    }
}
