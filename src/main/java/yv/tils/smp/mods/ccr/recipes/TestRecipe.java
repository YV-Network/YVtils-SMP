package yv.tils.smp.mods.ccr.recipes;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yv.tils.smp.internalapi.Log;

import java.util.*;

/**
 * @since 4.6.7
 * @version 4.6.7
 * @deprecated
 */
public class TestRecipe {

    private final ItemStack NInPut = new ItemStack(Material.DIRT, 1);
    private final ItemStack NUpgrade1 = new ItemStack(Material.DIRT, 1);
    private final ItemStack NUpgrade2 = new ItemStack(Material.DIRT, 1);

    public boolean on_Input(Inventory inv) {
        ItemStack InPut = new ItemStack(Material.DIRT, 1);
        ItemStack Upgrade1 = new ItemStack(Material.DIRT, 1);
        ItemStack Upgrade2 = new ItemStack(Material.DIRT, 1);

        if (inv.getItem(20) != null) {
            InPut.setItemMeta(inv.getItem(20).getItemMeta());

            new Log(inv.getItem(20).getItemMeta() + " " + InPut.getItemMeta());
        }

        ItemStack G1 = new ItemStack(Material.AIR);
        ItemStack G2 = new ItemStack(Material.AIR);
        ItemStack G3 = new ItemStack(Material.AIR);

        if (inv.getItem(20) != null) G1 = inv.getItem(20);
        if (inv.getItem(13) != null) G2 = inv.getItem(13);
        if (inv.getItem(31) != null) G3 = inv.getItem(31);

        if (Objects.equals(G1.getType(), InPut.getType()) && Objects.equals(G2.getType(), Upgrade1.getType()) && Objects.equals(G3.getType(), Upgrade2.getType())) {
            if (inv.getItem(20).getAmount() >= InPut.getAmount() && inv.getItem(13).getAmount() >= Upgrade1.getAmount() && inv.getItem(31).getAmount() >= Upgrade2.getAmount()) {
                return true;
            }
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

        int InN = InPut.getAmount() / NInPut.getAmount();
        int U1N = Upgrade1.getAmount() / NUpgrade1.getAmount();
        int U2N = Upgrade2.getAmount() / NUpgrade2.getAmount();
        double smallest = InN;
        if (smallest > U1N) smallest = U1N;
        if (smallest > U2N) smallest = U2N;
        int multiplier = (int) smallest;

        ItemStack item = new ItemStack(Material.DIRT);
        item.setAmount(2*multiplier);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        final String Enchants = String.valueOf(InPut.getEnchantments());
        final String[] SplitedEnchants = Enchants.split(", ");


        for (int i = 0; i < InPut.getEnchantments().size(); i++) {

            final String[] Enchantment_Level;

            if (i == 0) {
                Enchantment_Level = SplitedEnchants[1].split("]=");
            } else if (i + 1 == InPut.getEnchantments().size()) {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");

                final String[] Last_Enchantment_Level = Enchantment_Level[1].split("}");
                final String InPutEnchantment = Enchantment_Level[0];
                final int InPutEnchantmentLevel = Integer.parseInt(Last_Enchantment_Level[0]);

                meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);

                break;
            } else {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");
            }

            final String InPutEnchantment = Enchantment_Level[0];
            final int InPutEnchantmentLevel = Integer.parseInt(Enchantment_Level[1]);

            meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);
        }

        meta.setDisplayName("§cPreview §8(§e" + 2*multiplier + "x§8)");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DIG_SPEED, 15, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        lore.add("§eWooden Dream");
        lore.add("Efficiency XV");
        lore.add("Unbreaking ထ");
        lore.add("§4Test Recipe");
        meta.setLore(lore);

        item.setItemMeta(meta);

        //Recipe Checker
        ItemStack recipe_check = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta_recipe_check = recipe_check.getItemMeta();
        meta_recipe_check.setDisplayName(" ");
        recipe_check.setItemMeta(meta_recipe_check);

        for (int i : new int[]{10, 19, 28, 16, 25, 34}) {
            inv.setItem(i, recipe_check);
        }

        return item;
    }

    public void outputDrop(Inventory inv, Player player) {
        ItemStack InPut = inv.getItem(20);
        ItemStack Upgrade1 = inv.getItem(13);
        ItemStack Upgrade2 = inv.getItem(31);

        int InN = InPut.getAmount() / NInPut.getAmount();
        int U1N = Upgrade1.getAmount() / NUpgrade1.getAmount();
        int U2N = Upgrade2.getAmount() / NUpgrade2.getAmount();
        double smallest = InN;
        if (smallest > U1N) smallest = U1N;
        if (smallest > U2N) smallest = U2N;
        int multiplier = (int) smallest;

        InPut.setAmount((int) (InPut.getAmount()-smallest));
        Upgrade1.setAmount((int) (Upgrade1.getAmount()-smallest));
        Upgrade2.setAmount((int) (Upgrade2.getAmount()-smallest));


        ItemStack item = new ItemStack(Material.DIRT);
        item.setAmount(2*multiplier);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        final String Enchants = String.valueOf(InPut.getEnchantments());
        final String[] SplitedEnchants = Enchants.split(", ");


        for (int i = 0; i < InPut.getEnchantments().size(); i++) {

            final String[] Enchantment_Level;

            if (i == 0) {
                Enchantment_Level = SplitedEnchants[1].split("]=");
            } else if (i + 1 == InPut.getEnchantments().size()) {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");

                final String[] Last_Enchantment_Level = Enchantment_Level[1].split("}");
                final String InPutEnchantment = Enchantment_Level[0];
                final int InPutEnchantmentLevel = Integer.parseInt(Last_Enchantment_Level[0]);

                meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);

                break;
            } else {
                Enchantment_Level = SplitedEnchants[i + i + 1].split("]=");
            }

            final String InPutEnchantment = Enchantment_Level[0];
            final int InPutEnchantmentLevel = Integer.parseInt(Enchantment_Level[1]);

            meta.addEnchant(Enchantment.getByName(InPutEnchantment), InPutEnchantmentLevel, true);
        }

        meta.setDisplayName("§eWooden Dream");
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DIG_SPEED, 15, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        lore.add("Efficiency XV");
        lore.add("Unbreaking ထ");
        lore.add("§4Test Recipe");
        meta.setLore(lore);

        item.setItemMeta(meta);

        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), item);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), InPut);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), Upgrade1);
        player.getWorld().dropItem(player.getLocation().add(0, 1, -0.25), Upgrade2);
    }
}
