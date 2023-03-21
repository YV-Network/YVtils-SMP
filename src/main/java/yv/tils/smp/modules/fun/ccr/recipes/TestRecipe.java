package yv.tils.smp.modules.fun.ccr.recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yv.tils.smp.logger.ConsoleLog;

import java.util.*;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class TestRecipe {
    public boolean on_Input(Inventory inv) {
        ItemStack InPut = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemStack Upgrade1 = new ItemStack(Material.NETHERITE_INGOT);
        ItemStack Upgrade2 = new ItemStack(Material.BEACON);

        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new ConsoleLog(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        if (Objects.equals(inv.getItem(20), InPut) && Objects.equals(inv.getItem(13), Upgrade1) && Objects.equals(inv.getItem(31), Upgrade2)) {
            return true;
        }
        return false;
    }
    
    public ItemStack on_Output_create(Inventory inv) {
        ItemStack InPut = inv.getItem(20);
        ItemStack Upgrade1 = inv.getItem(13);
        ItemStack Upgrade2 = inv.getItem(31);

        if (InPut.getEnchantments() != null) {
            InPut.getEnchantments();
        }


        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        final String Enchants = String.valueOf(InPut.getEnchantments());
        final String[] SplitedEnchants = Enchants.split(", ");


        for (int i = 0; i <  InPut.getEnchantments().size(); i++) {

            final String[] Enchantment_Level;

            if (i == 0) {
                Enchantment_Level = SplitedEnchants[1].split("]=");
            }else if (i + 1 == InPut.getEnchantments().size()) {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");

                final String[] Last_Enchantment_Level = Enchantment_Level[1].split("}");
                final String InPutEnchantment = Enchantment_Level[0];
                final int InPutEnchantmentLevel = Integer.parseInt(Last_Enchantment_Level[0]);

                meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);

                break;
            }else {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");
            }

            final String InPutEnchantment = Enchantment_Level[0];
            final int InPutEnchantmentLevel = Integer.parseInt(Enchantment_Level[1]);

            meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);
        }

        meta.setDisplayName("§eWooden Dream");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DIG_SPEED, 15, true);
        lore.add("TestRecipe");
        lore.add("Legal Tool");
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
