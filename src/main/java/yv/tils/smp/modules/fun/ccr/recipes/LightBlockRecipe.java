package yv.tils.smp.modules.fun.ccr.recipes;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class LightBlockRecipe {

    ItemStack InPut = new ItemStack(Material.LANTERN);
    ItemStack InPut2 = new ItemStack(Material.SOUL_LANTERN);
    ItemStack Upgrade1 = new ItemStack(Material.GLASS_PANE);
    ItemStack Upgrade2 = new ItemStack(Material.IRON_NUGGET);

    public boolean on_Input_NL(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new ConsoleLog(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        if (Objects.equals(inv.getItem(20), InPut) && Objects.equals(inv.getItem(13), Upgrade1) && Objects.equals(inv.getItem(31), Upgrade2)) {
            return true;
        }
        return false;
    }
    public boolean on_Input_SL(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut2.setItemMeta(inv.getItem(20).getItemMeta());

            new ConsoleLog(inv.getItem(20).getItemMeta() + " " + InPut2.getItemMeta());
        }

        if (Objects.equals(inv.getItem(20), InPut2) && Objects.equals(inv.getItem(13), Upgrade1) && Objects.equals(inv.getItem(31), Upgrade2)) {
            return true;
        }
        return false;
    }
    
    public ItemStack on_Output_create(Inventory inv) {
        if (InPut.getEnchantments() != null) {
            InPut.getEnchantments();
        }

        ItemStack item = new ItemStack(Material.LIGHT, 8);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§eLight Block");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Invisibility I");
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§eLicht Block");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Unsichtbarkeit I");
        }

        meta.setLore(lore);
        
        item.setItemMeta(meta);

        //Recipe Checker
        ItemStack recipe_check = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta_recipe_check = recipe_check.getItemMeta();
        meta_recipe_check.setDisplayName(" ");
        recipe_check.setItemMeta(meta_recipe_check);

        for (int i : new int[]{10,19,28,16,25,34}) {
            inv.setItem(i, recipe_check);
        }

        return item;
    }
}
