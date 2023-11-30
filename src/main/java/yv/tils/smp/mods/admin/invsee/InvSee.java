package yv.tils.smp.mods.admin.invsee;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class InvSee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;

        if (!player.hasPermission("yvtils.smp.command.invsee")) {
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.MISSING_PERMISSION) + " yvtils.smp.command.invsee");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null){
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_NOT_ONLINE));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        player.openInventory(getInventory(target));
        return false;
    }


    public Inventory getInventory(Player player){

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("PLAYER");
        list2.add(player.getName());

        Inventory inv = Bukkit.createInventory(null, 54, new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_INVSEE_INVENTORY),list1 ,list2));

        ItemStack[] armour = player.getInventory().getArmorContents();
        ItemStack[] invContent = player.getInventory().getStorageContents();

        List<ItemStack> contents = new ArrayList<>(Arrays.asList(invContent));

        for (int i = 0; i < 9; i++){
            contents.add(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        Collections.addAll(contents, armour);

        ItemStack[] cont = contents.toArray(new ItemStack[0]);

        inv.setContents(cont);
        return inv;
    }
}
