package yv.tils.smp.modules.fun.ccr;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class CCRCommand implements CommandExecutor {

    //yvtils.smp.command.ccr

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Inventory inv = Bukkit.createInventory(player, 45, "§9CCR - Custom Crafting Recipes");

            //GUI Filler
            ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta_pane = pane.getItemMeta();
            meta_pane.setDisplayName(" ");
            pane.setItemMeta(meta_pane);

            for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,11,12,14,15,17,18,21,22,23,24,26,27,29,30,32,33,35}) {
                inv.setItem(i, pane);
            }

            //Accept Crafting
            ItemStack crafting_accept = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta meta_crafting_accept = crafting_accept.getItemMeta();
            meta_crafting_accept.setDisplayName("§aACCEPT CRAFTING RECIPE");
            crafting_accept.setItemMeta(meta_crafting_accept);

            for (int i : new int[]{36,37,38,39,40,41,42,43,44}) {
                inv.setItem(i, crafting_accept);
            }

            //Recipe Checker
            ItemStack recipe_check = new ItemStack(Material.RED_WOOL);
            ItemMeta meta_recipe_check = recipe_check.getItemMeta();
            meta_recipe_check.setDisplayName(" ");
            recipe_check.setItemMeta(meta_recipe_check);

            for (int i : new int[]{10,19,28,16,25,34}) {
                inv.setItem(i, recipe_check);
            }



            player.openInventory(inv);
        }
        return false;
    }
}
