package yv.tils.smp.modules.fun.ccr.recipes;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;

import java.io.*;
import java.util.*;


/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class InvFrameRecipe {

    ItemStack InPut = new ItemStack(Material.ITEM_FRAME);
    ItemStack Upgrade1 = new ItemStack(Material.GLASS_PANE);
    ItemStack Upgrade2 = new ItemStack(Material.IRON_NUGGET);

    public boolean on_Input_NF(Inventory inv) {
        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new ConsoleLog(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        if (Objects.equals(inv.getItem(20), InPut) && Objects.equals(inv.getItem(13), Upgrade1) && Objects.equals(inv.getItem(31), Upgrade2)) {
            return true;
        }
        return false;
    }
    public boolean on_Input_GF(Inventory inv) {
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
        if (InPut.getEnchantments() != null) {
            InPut.getEnchantments();
        }

        ItemStack item = new ItemStack(Material.ITEM_FRAME, 4);
        List<String> lore = new ArrayList<>();

        ItemStack invis = null;
        try {
            String invismeta ="rO0ABXcEAAAAG3NyABpvcmcuYnVra2l0LnV0aWwuaW8uV3JhcHBlcvJQR+zxEm8FAgABTAADbWFwdAAPTGphdmEvdXRpbC9NYXA7eHBzcgA1Y29tLmdvb2dsZS5jb21tb24uY29sbGVjdC5JbW11dGFibGVNYXAkU2VyaWFsaXplZEZvcm0AAAAAAAAAAAIAAkwABGtleXN0ABJMamF2YS9sYW5nL09iamVjdDtMAAZ2YWx1ZXNxAH4ABHhwdXIAE1tMamF2YS5sYW5nLk9iamVjdDuQzlifEHMpbAIAAHhwAAAABHQAAj09dAABdnQABHR5cGV0AARtZXRhdXEAfgAGAAAABHQAHm9yZy5idWtraXQuaW52ZW50b3J5Lkl0ZW1TdGFja3NyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAwwdAAKSVRFTV9GUkFNRXNxAH4AAHNxAH4AA3VxAH4ABgAAAANxAH4ACHQACW1ldGEtdHlwZXQACGludGVybmFsdXEAfgAGAAAAA3QACEl0ZW1NZXRhdAAKRU5USVRZX1RBR3QAREg0c0lBQUFBQUFBQS8rTmlZT0JpNEhUTks4a3NxUXhKVEdkazRQVE1LOHNzemt6S1NXVmtZQUFBd3RIM3BoNEFBQUE9cHBwcHBwcHBwcHBwcHBwcHBwcHBwcHBwcHA=";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(invismeta));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            invis = (ItemStack) dataInput.readObject();
            dataInput.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ItemMeta meta = invis.getItemMeta();

        if (SMPPlugin.getInstance().getConfig().getString("Language").equals("en")) {
            meta.setDisplayName("§eInvisible Item Frame");
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            lore.add("Invisibility I");
        }else if (SMPPlugin.getInstance().getConfig().getString("Language").equals("de")) {
            meta.setDisplayName("§Unsichtbarer Rahmen");
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
